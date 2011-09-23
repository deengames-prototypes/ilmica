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
            string selectedSet = this.uxQuestionSetList.SelectedText;
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

        private void uxDelete_Click(object sender, EventArgs e)
        {
            DialogResult r = MessageBox.Show("Are you sure you want to delete the current question?", "Confirm Delete?", MessageBoxButtons.YesNo);
            if (r == System.Windows.Forms.DialogResult.Yes)
            {
                string currentQuestion = this.uxCurrentQuestion.Text;
                if (this.uxQuestionSetList.SelectedIndex >= 0)
                {
                    this._sets[this.uxQuestionSetList.SelectedText].Remove(currentQuestion);
                }

                this.uxCurrentQuestion.Clear();
            }
        }

        private void uxSave_Click(object sender, EventArgs e)
        {
            string oldText = this.uxQuestions.SelectedItem.ToString();
            // Delete old version
            this._sets[this.uxQuestionSetList.SelectedText].Remove(oldText);
            // Add new
            this._sets[this.uxQuestionSetList.SelectedText].Add(this.uxCurrentQuestion.Text);
        }

        private void uxSaveAsNew_Click(object sender, EventArgs e)
        {
            this._sets[this.uxQuestionSetList.SelectedText].Add(this.uxCurrentQuestion.Text);
        }
    }
}
