package com.deengames.ilmica.screens;

import java.util.ArrayList;

import com.deengames.ilmica.controller.MainController;
import com.deengames.ilmica.model.Question;
import com.deengames.ilmica.model.QuestionMetaDataType;
import com.deengames.radiantwrench.utils.ClickListener;
import com.deengames.radiantwrench.utils.Clickable;
import com.deengames.radiantwrench.view.ImageCheckbox;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.SpriteSheet;
import com.deengames.radiantwrench.view.Text;

public class QuizScreen extends Screen {
	
	private String _setName;
	private Question[] _questions;
	
	private Text _questionText;
	
	private final int QUESTION_TEXT_HORIZONTAL_OFFSET = 32;
	private final int QUESTION_TEXT_VERTICAL_OFFSET = 44;
	private final int ANSWER_HORIZONTAL_OFFSET = 72;
	private final int ANSWERS_PADDING_FROM_QUESTION = 36;
	private final int SPACE_BETWEEN_ANSWERS = 32;
	private final int CHECKMARK_IMAGE_HORIZONTAL_WHITESPACE_OFFSET = 4;
	private final int CHECKMARK_IMAGE_VERTICAL_WHITESPACE_OFFSET = 10;
	private final int QUESTION_HEADER_OFFSET = 12;
	private final int INFO_PANEL_PADDING = 12;
	private final int INFO_PANEL_MARGIN = 12;
	
	ImageCheckbox _clickedCheckbox = null;
	private SpriteSheet _previousButton = null;
	private SpriteSheet _nextButton = null;
	Sprite _halfBlackOut = null;
	private Text _questionHeaderText = null;
	private Sprite _infoIcon = null;
	private Sprite _infoPanel = null;
	private Text _infoPanelText = null;
	private int[] _selectedAnswerIndicies;
	
	private ArrayList<ImageCheckbox> _answersCheckboxes = new ArrayList<ImageCheckbox>();
	private ArrayList<Text> _answersTexts = new ArrayList<Text>();
	
	// TODO: move into model?
	int _currentQuestionIndex = 0;
	
	public QuizScreen(String setName) {
		this._setName = setName;
	}
	
	@Override
	public void initialize() {
		super.initialize();
		
		this.fadeOutImmediately();
		
		this._halfBlackOut = this.addSprite("content/images/half-black.png");
		this._halfBlackOut.setAlpha(0);
		this._halfBlackOut.setZ(Integer.MAX_VALUE - 1);
		
		this._questions = MainController.makeQuizForSet(this._setName);	
		this._selectedAnswerIndicies = new int[this._questions.length];
		// Le sigh...
		for (int i = 0; i < this._selectedAnswerIndicies.length; i++) {
			this._selectedAnswerIndicies[i] = -1;
		}
		
		this.addSprite("content/images/mainBackground.jpg");
		
		this._questionHeaderText = this.addText("");
		this._questionHeaderText.setFontSize(24);
		this._questionHeaderText.setY(QUESTION_HEADER_OFFSET);
		
		this._questionText = this.addText("");
		this._questionText.setFontSize(14);
		this._questionText.setX(QUESTION_TEXT_HORIZONTAL_OFFSET);
		this._questionText.setY(this._questionHeaderText.getY() + QUESTION_TEXT_VERTICAL_OFFSET);
		
		// Equal out with our LHS padding
		this._questionText.setMaxWidth(this.getWidth() - (2 * QUESTION_TEXT_HORIZONTAL_OFFSET));
		
		Sprite doneButton = this.addSprite("content/images/checkmark.png");
		doneButton.setX((this.getWidth() - doneButton.getWidth()) / 2);
		doneButton.setY(this.getHeight() - doneButton.getHeight());
		doneButton.setClickListener(new ClickListener() {
			public void onClick(Clickable clickable) {
				_halfBlackOut.setAlpha(1);
			}
		});
		
		this._previousButton = this.addSpriteSheet("content/images/left-arrow.png", 64, 64);
		this._previousButton.setY(this.getHeight() - this._previousButton.getHeight());
		this._previousButton.setClickListener(new ClickListener() {
			public void onClick(Clickable clickable) {
				showPreviousQuestionIfPossible();
			}
		});
		
		this._nextButton = this.addSpriteSheet("content/images/right-arrow.png", 64, 64);
		this._nextButton.setX(this.getWidth() - this._nextButton.getWidth());
		this._nextButton.setY(this.getHeight() - this._nextButton.getHeight());
		this._nextButton.setClickListener(new ClickListener() {
			public void onClick(Clickable clickable) {
				showNextQuestionIfPossible();
			}
		});

		this._infoIcon = this.addSprite("content/images/info.png");
		this._infoIcon.setX(this.getWidth() - this._infoIcon.getWidth()- QUESTION_HEADER_OFFSET);
		this._infoIcon.setY(QUESTION_HEADER_OFFSET);
		this._infoIcon.setClickListener(new ClickListener() {
			public void onClick(Clickable clickable) {
				toggleShowingCurrentQuestionInformation();
			}
		});
		
		this._infoPanel = this.addSprite("content/images/metal-panel.png");
		this._infoPanel.setX(this.getWidth() - this._infoPanel.getWidth() - this._infoIcon.getWidth() - INFO_PANEL_PADDING); 
		this._infoPanel.setY(this._infoIcon.getHeight() + INFO_PANEL_PADDING);
		this._infoPanel.setAlpha(0);
		this._infoPanel.setZ(1000);
		
		this._infoPanelText = this.addText("HEYYYYYYYYYYYYY OVER HERE");
		this._infoPanelText.setX(this._infoPanel.getX() + INFO_PANEL_MARGIN);
		this._infoPanelText.setY(this._infoPanel.getY() + INFO_PANEL_MARGIN);
		this._infoPanelText.setMaxWidth(this._infoPanel.getWidth() - (2 * INFO_PANEL_MARGIN));
		this._infoPanelText.setFontSize(12);
		this._infoPanelText.setIsVisible(false);
		this._infoPanelText.setZ(this._infoPanel.getZ() + 1);
		
		showCurrentQuestion();
		
		this.fadeIn();
	}

	private void hideCurentQuestionInformation() {
		this._infoPanel.setAlpha(1);
		this.toggleShowingCurrentQuestionInformation();
	}
	
	void toggleShowingCurrentQuestionInformation() {
		this._infoPanel.setAlpha(1 - this._infoPanel.getAlpha());
		this._infoPanelText.setIsVisible(this._infoPanel.getAlpha() == 0 ? false : true);
	}

	void showPreviousQuestionIfPossible() {
		if (this._currentQuestionIndex > 0) {
			this._currentQuestionIndex--;
			this.showCurrentQuestion();
			this.hideCurentQuestionInformation();
		}
	}
	
	void showNextQuestionIfPossible() {
		if (this._currentQuestionIndex < this._questions.length - 1) {
			this._currentQuestionIndex++;
			this.showCurrentQuestion();
			this.hideCurentQuestionInformation();
		}
	}
	
	private void setupNextAndPreviousControls(int questionNumber) {
		if (questionNumber > 0) {
			this._previousButton.setFrameIndex(1);
		} else {
			this._previousButton.setFrameIndex(0);
		}
		
		if (questionNumber == this._questions.length - 1) {
			this._nextButton.setFrameIndex(0);
		} else {
			this._nextButton.setFrameIndex(1);
		}
	}

	private void showCurrentQuestion() {		
		showQuestionAndAnswers(this._currentQuestionIndex);
		setupNextAndPreviousControls(this._currentQuestionIndex);
	}

	private void showQuestionAndAnswers(int index) {
		clearPreviousAnswerControls();
		
		this._questionHeaderText.setDisplayText("Question " + (index + 1) + "/" + this._questions.length);
		this._questionHeaderText.setX((this.getWidth() - this._questionHeaderText.getWidth()) / 2);
		
		this._questionText.setDisplayText(this._questions[index].getText());
		this._infoPanelText.setDisplayText(this._questions[index].getMetaData(QuestionMetaDataType.INFORMATION));
		String[] answers = this._questions[index].getAnswers();
		
		for (int i = 0; i < answers.length; i++) {
			Text t = this.addText(answers[i]);
			t.setX(ANSWER_HORIZONTAL_OFFSET);
			t.setY(this._questionText.getY() + 
					ANSWERS_PADDING_FROM_QUESTION + 
					this._questionText.getHeight() + (SPACE_BETWEEN_ANSWERS * i));
			t.setFontSize(12);
			this._answersTexts.add(t);
			
			ImageCheckbox c = this.addImageCheckbox();
			c.x = t.getX() - c.getScaledWidth() - CHECKMARK_IMAGE_HORIZONTAL_WHITESPACE_OFFSET;
			c.y = t.getY() - CHECKMARK_IMAGE_VERTICAL_WHITESPACE_OFFSET;
			
			c.setClickListener(new ClickListener() {
				public void onClick(Clickable clickable) {
					_clickedCheckbox = (ImageCheckbox)clickable;
					checkedQuestionChanged();
				}			
			});
			
			if (this._selectedAnswerIndicies[index] == i) {
				c.setIsChecked(true);
			}
			
			this._answersCheckboxes.add(c);
		}
	}
	
	private void clearPreviousAnswerControls() {
		this._texts.removeAll(this._answersTexts);
		this._imageCheckBoxes.removeAll(this._answersCheckboxes);
		this._answersCheckboxes.clear();
		this._answersTexts.clear();
	}

	/**
	 * Uncheck every checkbox except the checked guy.
	 * Essentially, this is like a set of radio buttons.
	 */
	public void checkedQuestionChanged() {
		for (ImageCheckbox c : this._imageCheckBoxes) {
			if (c == this._clickedCheckbox && c.getIsChecked() == true) {
				c.setIsChecked(true);
			} else {
				c.setIsChecked(false);
			}
		}
		
		// Store the checked index
		for (int i = 0; i < this._answersCheckboxes.size(); i++) {
			if (this._answersCheckboxes.get(i) == this._clickedCheckbox) {
				this._selectedAnswerIndicies[this._currentQuestionIndex] = i;
			}
		}
	}
}
