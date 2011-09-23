namespace QuestionMaker
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.uxQuestionSetList = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.uxDeleteSet = new System.Windows.Forms.Button();
            this.uxAddSet = new System.Windows.Forms.Button();
            this.uxQuestions = new System.Windows.Forms.ListBox();
            this.label2 = new System.Windows.Forms.Label();
            this.uxCurrentQuestion = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.uxDelete = new System.Windows.Forms.Button();
            this.uxSave = new System.Windows.Forms.Button();
            this.uxSaveAsNew = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // uxQuestionSetList
            // 
            this.uxQuestionSetList.FormattingEnabled = true;
            this.uxQuestionSetList.Location = new System.Drawing.Point(90, 13);
            this.uxQuestionSetList.Name = "uxQuestionSetList";
            this.uxQuestionSetList.Size = new System.Drawing.Size(520, 21);
            this.uxQuestionSetList.TabIndex = 0;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(13, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(71, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "Question Set:";
            // 
            // uxDeleteSet
            // 
            this.uxDeleteSet.Location = new System.Drawing.Point(697, 13);
            this.uxDeleteSet.Name = "uxDeleteSet";
            this.uxDeleteSet.Size = new System.Drawing.Size(75, 23);
            this.uxDeleteSet.TabIndex = 2;
            this.uxDeleteSet.Text = "Delete Set";
            this.uxDeleteSet.UseVisualStyleBackColor = true;
            this.uxDeleteSet.Click += new System.EventHandler(this.uxDeleteSet_Click);
            // 
            // uxAddSet
            // 
            this.uxAddSet.Location = new System.Drawing.Point(616, 13);
            this.uxAddSet.Name = "uxAddSet";
            this.uxAddSet.Size = new System.Drawing.Size(75, 23);
            this.uxAddSet.TabIndex = 3;
            this.uxAddSet.Text = "Add Set";
            this.uxAddSet.UseVisualStyleBackColor = true;
            // 
            // uxQuestions
            // 
            this.uxQuestions.FormattingEnabled = true;
            this.uxQuestions.Location = new System.Drawing.Point(12, 53);
            this.uxQuestions.Name = "uxQuestions";
            this.uxQuestions.ScrollAlwaysVisible = true;
            this.uxQuestions.Size = new System.Drawing.Size(260, 472);
            this.uxQuestions.TabIndex = 4;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(13, 37);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(57, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "Questions:";
            // 
            // uxCurrentQuestion
            // 
            this.uxCurrentQuestion.Location = new System.Drawing.Point(278, 69);
            this.uxCurrentQuestion.Multiline = true;
            this.uxCurrentQuestion.Name = "uxCurrentQuestion";
            this.uxCurrentQuestion.Size = new System.Drawing.Size(494, 454);
            this.uxCurrentQuestion.TabIndex = 6;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(278, 53);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(31, 13);
            this.label3.TabIndex = 7;
            this.label3.Text = "Text:";
            // 
            // uxDelete
            // 
            this.uxDelete.Location = new System.Drawing.Point(12, 531);
            this.uxDelete.Name = "uxDelete";
            this.uxDelete.Size = new System.Drawing.Size(75, 23);
            this.uxDelete.TabIndex = 8;
            this.uxDelete.Text = "Delete";
            this.uxDelete.UseVisualStyleBackColor = true;
            this.uxDelete.Click += new System.EventHandler(this.uxDelete_Click);
            // 
            // uxSave
            // 
            this.uxSave.Location = new System.Drawing.Point(697, 531);
            this.uxSave.Name = "uxSave";
            this.uxSave.Size = new System.Drawing.Size(75, 23);
            this.uxSave.TabIndex = 9;
            this.uxSave.Text = "Save (Edit)";
            this.uxSave.UseVisualStyleBackColor = true;
            this.uxSave.Click += new System.EventHandler(this.uxSave_Click);
            // 
            // uxSaveAsNew
            // 
            this.uxSaveAsNew.Location = new System.Drawing.Point(606, 531);
            this.uxSaveAsNew.Name = "uxSaveAsNew";
            this.uxSaveAsNew.Size = new System.Drawing.Size(85, 23);
            this.uxSaveAsNew.TabIndex = 10;
            this.uxSaveAsNew.Text = "Save As New";
            this.uxSaveAsNew.UseVisualStyleBackColor = true;
            this.uxSaveAsNew.Click += new System.EventHandler(this.uxSaveAsNew_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(784, 562);
            this.Controls.Add(this.uxSaveAsNew);
            this.Controls.Add(this.uxSave);
            this.Controls.Add(this.uxDelete);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.uxCurrentQuestion);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.uxQuestions);
            this.Controls.Add(this.uxAddSet);
            this.Controls.Add(this.uxDeleteSet);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.uxQuestionSetList);
            this.Name = "Form1";
            this.Text = "iLMica Question Maker";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox uxQuestionSetList;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button uxDeleteSet;
        private System.Windows.Forms.Button uxAddSet;
        private System.Windows.Forms.ListBox uxQuestions;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox uxCurrentQuestion;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button uxDelete;
        private System.Windows.Forms.Button uxSave;
        private System.Windows.Forms.Button uxSaveAsNew;
    }
}

