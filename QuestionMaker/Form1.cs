using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace QuestionMaker
{
    public partial class Form1 : Form
    {

        private string UNASSIGNED_SET = "(Unassigned)";
        private Dictionary<string, List<string>> _sets = new Dictionary<string, List<string>>();

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            this.uxQuestionSetList.Items.Add(UNASSIGNED_SET);
            this._sets.Add(UNASSIGNED_SET, new List<string>());
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
                        List<string> _setsQuestions = this._sets[selectedSet];
                        // Reassign questions to unassigned
                        foreach (string question in _setsQuestions)
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
                string currentQuestion = this.uxCurrentQuestion.Text;
                if (this.uxQuestionSetList.SelectedIndex >= 0)
                {
                    this._sets[this.uxQuestionSetList.SelectedItem.ToString()].Remove(currentQuestion);
                }

                this.uxCurrentQuestion.Clear();

				refreshQuestionList();
            }
        }

        private void uxSave_Click(object sender, EventArgs e)
        {            
            int selected = this.uxQuestions.SelectedIndex;
            if (selected > -1)
            {
                string oldText = this.uxQuestions.SelectedItem.ToString();
                // Delete old version
                this._sets[this.uxQuestionSetList.SelectedText].Remove(oldText);
                // Add new
                this._sets[this.uxQuestionSetList.SelectedText].Add(this.uxCurrentQuestion.Text);
                this.refreshQuestionList();
            }
            else
            {
                MessageBox.Show("Select a question to edit first!");
            }
        }

        private void refreshQuestionList()
        {
            this.uxQuestions.Items.Clear();
            if (this.uxQuestionSetList.SelectedIndex > -1)
            {
                List<string> questions = this._sets[this.uxQuestionSetList.SelectedItem.ToString()];
                questions.Sort();
                foreach (string q in questions)
                {
                    this.uxQuestions.Items.Add(q);
                }
            }
        }

        private void uxSaveAsNew_Click(object sender, EventArgs e)
        {
            if (this.uxQuestionSetList.SelectedIndex == -1 || this.uxQuestionSetList.Items.Count == 1)
            {
                this.uxQuestionSetList.SelectedIndex = 0;
                this.uxQuestionSetList.SelectAll();
            }

            this._sets[this.uxQuestionSetList.SelectedItem.ToString()].Add(this.uxCurrentQuestion.Text);

            this.uxQuestions.Items.Add(this.uxCurrentQuestion.Text);
        }

        private void uxQuestions_SelectedIndexChanged(object sender, EventArgs e)
        {
            this.uxCurrentQuestion.Text = this.uxQuestions.SelectedItem.ToString();
        }

		private void uxAddSet_Click(object sender, EventArgs e)
		{
			bool clickedCancel = false;			
			string name = showInputBox("Set name?", "Set Name", ref clickedCancel);
			if (clickedCancel == false && !string.IsNullOrEmpty(name))
			{
				this._sets[name] = new List<string>();
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
    }
}
