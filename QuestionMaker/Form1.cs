﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace QuestionMaker
{
    public partial class Form1 : Form
    {

        private string UNASSIGNED_SET = "(Unassigned)";
		private Dictionary<string, List<Question>> _sets = new Dictionary<string, List<Question>>();

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            this.uxQuestionSetList.Items.Add(UNASSIGNED_SET);
            this._sets.Add(UNASSIGNED_SET, new List<Question>());
        }

        private void uxDeleteSet_Click(object sender, EventArgs e)
        {
            if (this.uxQuestionSetList.SelectedIndex == -1)
            {
                MessageBox.Show("Select a set first to delete!");
            }
            else
            {
                string selectedSet = this.uxQuestionSetList.SelectedItem.ToString();
                if (selectedSet == UNASSIGNED_SET)
                {
                    MessageBox.Show("You can't delete the unassigned set. Duh!");
                }
                else
                {
                    DialogResult r = MessageBox.Show("Are you sure you want to delete the set " + selectedSet + "? All the questions will become unassigned.", "Confirm Delete?", MessageBoxButtons.YesNo);
                    if (r == System.Windows.Forms.DialogResult.Yes)
                    {
                        List<Question> _setsQuestions = this._sets[selectedSet];
                        // Reassign questions to unassigned
                        foreach (Question question in _setsQuestions)
                        {
                            this._sets[UNASSIGNED_SET].Add(question);
                        }

                        this._sets.Remove(selectedSet);
                        this.uxQuestionSetList.Items.Remove(this.uxQuestionSetList.SelectedIndex);
                    }
                }
            }
        }

        private void uxDelete_Click(object sender, EventArgs e)
        {
            DialogResult r = MessageBox.Show("Are you sure you want to delete the current question?", "Confirm Delete?", MessageBoxButtons.YesNo);
            if (r == System.Windows.Forms.DialogResult.Yes)
            {
                removeQuestionFromCurrentSet(this.uxCurrentQuestion.Text);
                this.uxCurrentQuestion.Clear();

				refreshQuestionList();
            }
        }

        private void uxSave_Click(object sender, EventArgs e)
        {
            if (fieldsAreValid())
            {
                int selected = this.uxQuestions.SelectedIndex;
                if (selected > -1)
                {
                    string oldText = this.uxQuestions.SelectedItem.ToString();

                    // Remove
                    removeQuestionFromCurrentSet(oldText);
                    List<Question> currentSet = this._sets[this.uxQuestionSetList.SelectedText];

                    // Re-add
                    Question q = new Question();
                    q.Text = this.uxCurrentQuestion.Text;
                    q.Answers = this.uxAnswers.Text;
                    currentSet.Add(q);

                    this.refreshQuestionList();
                }
                else
                {
                    MessageBox.Show("Select a question to edit first!");
                }
            }
        }

        private bool fieldsAreValid()
        {
            string answers = this.uxAnswers.Text;

            if (!this.uxCurrentQuestion.Text.Any()) {
                showError("You need to specify question content!");
                return false;
            }
            else if (!answers.Any() || answers.Count(c => c == '\n') < 1)
            {
                showError("You need to specify at least 2 answers!");
                return false;
            } else {
                string[] lines = answers.Split('\n');
                
                int count = 0;
                foreach (string line in lines)
                {
                    if (line.StartsWith("*"))
                    {
                        count++;
                    }
                }

                if (count != 1)
                {
                    showError("You need to specify only one best answer (starts with *)");
                    return false;
                }

                count = 0;
                foreach (string line in lines)
                {
                    if (!string.IsNullOrWhiteSpace(line))
                    {
                        count++;
                    }
                }

                if (count < 2)
                {
                    showError("You need to have at least two non-empty answers.");
                    return false;
                }

            }

            return true;
        }

        private void showError(string message)
        {
            MessageBox.Show(message, "Invalid Question", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }

        void removeQuestionFromCurrentSet(string text)
		{
			Question found = findQuestionInCurrentSet(text);

			if (found != null)
			{
				this._sets[this.uxQuestionSetList.SelectedText].Remove(found);
			}
		}

		private Question findQuestionInCurrentSet(string text)
		{
			List<Question> currentSet = this._sets[this.uxQuestionSetList.SelectedItem.ToString()];

			foreach (Question q in currentSet)
			{
				if (q.Text == text)
				{
					return q;
				}
			}

			return null;
		}

        private void refreshQuestionList()
        {
            this.uxQuestions.Items.Clear();
            if (this.uxQuestionSetList.SelectedIndex > -1)
            {
                List<Question> questions = this._sets[this.uxQuestionSetList.SelectedItem.ToString()];
                foreach (Question q in questions)
                {
                    this.uxQuestions.Items.Add(q.ToString());
                }
            }
        }

        private void uxSaveAsNew_Click(object sender, EventArgs e)
        {
            if (fieldsAreValid())
            {
                if (this.uxQuestionSetList.SelectedIndex == -1 || this.uxQuestionSetList.Items.Count == 1)
                {
                    // Select the "UNASSIGNED" set if a set is not selected
                    this.uxQuestionSetList.SelectedIndex = 0;
                    this.uxQuestionSetList.SelectAll();
                }

                Question question = new Question();
                question.Text = this.uxCurrentQuestion.Text;
                question.Answers = this.uxAnswers.Text;
                this._sets[this.uxQuestionSetList.SelectedItem.ToString()].Add(question);
                this.uxQuestions.Items.Add(question.ToString());
            }
        }

        private void uxQuestions_SelectedIndexChanged(object sender, EventArgs e)
        {
            Question q = findQuestionInCurrentSet(this.uxQuestions.SelectedItem.ToString());
			if (q != null)
			{
				this.uxCurrentQuestion.Text = q.Text;
				this.uxAnswers.Text = q.Answers;
			}
        }

		private void uxAddSet_Click(object sender, EventArgs e)
		{
			bool clickedCancel = false;			
			string name = showInputBox("Set name?", "Set Name", ref clickedCancel);
			if (clickedCancel == false && !string.IsNullOrEmpty(name))
			{
				this._sets[name] = new List<Question>();
				this.uxQuestionSetList.Items.Add(name);
			}
		}

		private static string showInputBox(string promptText, string title, ref bool clickedCancel)
		{
			Form form = new Form();
			Label label = new Label();
			TextBox textBox = new TextBox();
			Button buttonOk = new Button();
			Button buttonCancel = new Button();

			form.Text = title;
			label.Text = promptText;

			buttonOk.Text = "OK";
			buttonCancel.Text = "Cancel";
			buttonOk.DialogResult = DialogResult.OK;
			buttonCancel.DialogResult = DialogResult.Cancel;

			label.SetBounds(9, 20, 372, 13);
			textBox.SetBounds(12, 36, 372, 20);
			buttonOk.SetBounds(228, 72, 75, 23);
			buttonCancel.SetBounds(309, 72, 75, 23);

			label.AutoSize = true;
			textBox.Anchor = textBox.Anchor | AnchorStyles.Right;
			buttonOk.Anchor = AnchorStyles.Bottom | AnchorStyles.Right;
			buttonCancel.Anchor = AnchorStyles.Bottom | AnchorStyles.Right;

			form.ClientSize = new Size(396, 107);
			form.Controls.AddRange(new Control[] { label, textBox, buttonOk, buttonCancel });
			form.ClientSize = new Size(Math.Max(300, label.Right + 10), form.ClientSize.Height);
			form.FormBorderStyle = FormBorderStyle.FixedDialog;
			form.StartPosition = FormStartPosition.CenterScreen;
			form.MinimizeBox = false;
			form.MaximizeBox = false;
			form.AcceptButton = buttonOk;
			form.CancelButton = buttonCancel;

			DialogResult dialogResult = form.ShowDialog();
			if (dialogResult == DialogResult.Cancel)
			{
				clickedCancel = true;
			}
			else
			{
				clickedCancel = false;
			}

			return textBox.Text;
		}

		private void uxQuestionSetList_SelectedIndexChanged(object sender, EventArgs e)
		{
			refreshQuestionList();
		}

		private void openToolStripMenuItem_Click(object sender, EventArgs e)
		{
			OpenFileDialog dialog = new OpenFileDialog();

			dialog.Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*";
			dialog.RestoreDirectory = true;
			
			if (dialog.ShowDialog() == DialogResult.OK) // Test result.
			{
				string file = dialog.FileName;
				try
				{
					parseData(File.ReadAllText(file));
				}
				catch (IOException i)
				{
					MessageBox.Show("Couldn't open " + file + ". Error details are: " + i.Message);
				}

				this.uxQuestionSetList.Items.Clear();
				foreach (string setName in this._sets.Keys.ToArray())
				{
					this.uxQuestionSetList.Items.Add(setName);
				}
			}
		}

		private void parseData(string sets)
		{
			string[] data = sets.Split(new char[] { '\n' });

			// Sigh, stateful parsing :/
			string currentSet = "";
			Question currentQuestion = null;

			foreach (string curLine in data)
			{
				string line = curLine.Trim();
				if (line.StartsWith("Set"))
				{
					// Save previous question
					if (currentQuestion != null)
					{
						_sets[currentSet].Add(currentQuestion);
						currentQuestion = null;
					}

					// It's a set
					currentSet = line.Substring(line.IndexOf(':') + 2);
					_sets[currentSet] = new List<Question>();
				}
				else if (line.StartsWith("Question:"))
				{
					// Save previous question
					if (currentQuestion != null)
					{
						_sets[currentSet].Add(currentQuestion);
						currentQuestion = null;
					}

					currentQuestion = new Question();
					currentQuestion.Text = line.Substring(line.IndexOf(':') + 2);
				}
				else if (line.StartsWith("Answer") || line.StartsWith("Best Answer"))
				{
					string answer = line.Substring(line.IndexOf(':') + 2);
					if (line.StartsWith("Best"))
					{
						answer = "*" + answer;
					}
					currentQuestion.Answers += answer + "\r\n";
				}
			}

			// Push the final question
			_sets[currentSet].Add(currentQuestion);
		}

		private void saveToolStripMenuItem_Click(object sender, EventArgs e)
		{
			SaveFileDialog dialog = new SaveFileDialog();

			dialog.Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*";
			dialog.RestoreDirectory = true;

			if (dialog.ShowDialog() == DialogResult.OK)
			{
				using (System.IO.StreamWriter file = new System.IO.StreamWriter(dialog.FileName))
				{
					foreach (string setName in this._sets.Keys.ToArray())
					{
						writeSet(setName, file);
					}
				}
			}
			else
			{
				MessageBox.Show("Couldn't open the file, sorry.", "Data Not Saved", MessageBoxButtons.OK, MessageBoxIcon.Error);
			}
		}

		private void writeSet(string setName, StreamWriter file)
		{
			List<Question> set = this._sets[setName];
			file.WriteLine("Set: " + setName);
			foreach (Question q in set)
			{
				file.WriteLine("  Question: " + q.Text);
				foreach (String answer in q.Answers.Split(new char[] { '\n' }))
				{
					string cleanAnswer = answer;

					file.Write("    ");
					if (answer.StartsWith("*"))
					{
						file.Write("Best ");
						cleanAnswer = answer.Substring(1);
					}
					else if (answer.StartsWith("-"))
					{
						cleanAnswer = answer.Substring(1);
					}

					if (cleanAnswer.Length > 0)
					{
						file.WriteLine("Answer: " + cleanAnswer.Trim());
					}
				}
			}
		}
    }
}
