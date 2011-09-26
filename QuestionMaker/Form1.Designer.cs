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
			this.uxAnswers = new System.Windows.Forms.TextBox();
			this.label4 = new System.Windows.Forms.Label();
			this.uxSaveAsNew = new System.Windows.Forms.Button();
			this.uxSave = new System.Windows.Forms.Button();
			this.uxMainMenu = new System.Windows.Forms.MenuStrip();
			this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
			this.saveToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.toolStripMenuItem2 = new System.Windows.Forms.ToolStripSeparator();
			this.exitToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.openToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.uxMainMenu.SuspendLayout();
			this.SuspendLayout();
			// 
			// uxQuestionSetList
			// 
			this.uxQuestionSetList.FormattingEnabled = true;
			this.uxQuestionSetList.Location = new System.Drawing.Point(90, 36);
			this.uxQuestionSetList.Name = "uxQuestionSetList";
			this.uxQuestionSetList.Size = new System.Drawing.Size(520, 21);
			this.uxQuestionSetList.TabIndex = 0;
			this.uxQuestionSetList.SelectedIndexChanged += new System.EventHandler(this.uxQuestionSetList_SelectedIndexChanged);
			// 
			// label1
			// 
			this.label1.AutoSize = true;
			this.label1.Location = new System.Drawing.Point(13, 36);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(71, 13);
			this.label1.TabIndex = 1;
			this.label1.Text = "Question Set:";
			// 
			// uxDeleteSet
			// 
			this.uxDeleteSet.Location = new System.Drawing.Point(697, 36);
			this.uxDeleteSet.Name = "uxDeleteSet";
			this.uxDeleteSet.Size = new System.Drawing.Size(75, 23);
			this.uxDeleteSet.TabIndex = 2;
			this.uxDeleteSet.Text = "Delete Set";
			this.uxDeleteSet.UseVisualStyleBackColor = true;
			this.uxDeleteSet.Click += new System.EventHandler(this.uxDeleteSet_Click);
			// 
			// uxAddSet
			// 
			this.uxAddSet.Location = new System.Drawing.Point(616, 36);
			this.uxAddSet.Name = "uxAddSet";
			this.uxAddSet.Size = new System.Drawing.Size(75, 23);
			this.uxAddSet.TabIndex = 3;
			this.uxAddSet.Text = "New Set";
			this.uxAddSet.UseVisualStyleBackColor = true;
			this.uxAddSet.Click += new System.EventHandler(this.uxAddSet_Click);
			// 
			// uxQuestions
			// 
			this.uxQuestions.FormattingEnabled = true;
			this.uxQuestions.Location = new System.Drawing.Point(12, 76);
			this.uxQuestions.Name = "uxQuestions";
			this.uxQuestions.ScrollAlwaysVisible = true;
			this.uxQuestions.Size = new System.Drawing.Size(260, 472);
			this.uxQuestions.TabIndex = 4;
			this.uxQuestions.SelectedIndexChanged += new System.EventHandler(this.uxQuestions_SelectedIndexChanged);
			// 
			// label2
			// 
			this.label2.AutoSize = true;
			this.label2.Location = new System.Drawing.Point(13, 60);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(57, 13);
			this.label2.TabIndex = 5;
			this.label2.Text = "Questions:";
			// 
			// uxCurrentQuestion
			// 
			this.uxCurrentQuestion.Location = new System.Drawing.Point(278, 92);
			this.uxCurrentQuestion.Multiline = true;
			this.uxCurrentQuestion.Name = "uxCurrentQuestion";
			this.uxCurrentQuestion.Size = new System.Drawing.Size(494, 298);
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
			this.uxDelete.Location = new System.Drawing.Point(12, 554);
			this.uxDelete.Name = "uxDelete";
			this.uxDelete.Size = new System.Drawing.Size(75, 23);
			this.uxDelete.TabIndex = 8;
			this.uxDelete.Text = "Delete";
			this.uxDelete.UseVisualStyleBackColor = true;
			this.uxDelete.Click += new System.EventHandler(this.uxDelete_Click);
			// 
			// uxAnswers
			// 
			this.uxAnswers.Location = new System.Drawing.Point(278, 409);
			this.uxAnswers.Multiline = true;
			this.uxAnswers.Name = "uxAnswers";
			this.uxAnswers.Size = new System.Drawing.Size(494, 139);
			this.uxAnswers.TabIndex = 11;
			// 
			// label4
			// 
			this.label4.AutoSize = true;
			this.label4.Location = new System.Drawing.Point(278, 393);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(240, 13);
			this.label4.TabIndex = 12;
			this.label4.Text = "Answers (one per line, best answer starts with a *)";
			// 
			// uxSaveAsNew
			// 
			this.uxSaveAsNew.Location = new System.Drawing.Point(606, 554);
			this.uxSaveAsNew.Name = "uxSaveAsNew";
			this.uxSaveAsNew.Size = new System.Drawing.Size(85, 23);
			this.uxSaveAsNew.TabIndex = 14;
			this.uxSaveAsNew.Text = "Save As New";
			this.uxSaveAsNew.UseVisualStyleBackColor = true;
			this.uxSaveAsNew.Click += new System.EventHandler(this.uxSaveAsNew_Click);
			// 
			// uxSave
			// 
			this.uxSave.Location = new System.Drawing.Point(697, 554);
			this.uxSave.Name = "uxSave";
			this.uxSave.Size = new System.Drawing.Size(75, 23);
			this.uxSave.TabIndex = 13;
			this.uxSave.Text = "Save (Edit)";
			this.uxSave.UseVisualStyleBackColor = true;
			this.uxSave.Click += new System.EventHandler(this.uxSave_Click);
			// 
			// uxMainMenu
			// 
			this.uxMainMenu.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripMenuItem1});
			this.uxMainMenu.Location = new System.Drawing.Point(0, 0);
			this.uxMainMenu.Name = "uxMainMenu";
			this.uxMainMenu.Size = new System.Drawing.Size(784, 24);
			this.uxMainMenu.TabIndex = 15;
			this.uxMainMenu.Text = "menuStrip1";
			// 
			// toolStripMenuItem1
			// 
			this.toolStripMenuItem1.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.openToolStripMenuItem,
            this.saveToolStripMenuItem,
            this.toolStripMenuItem2,
            this.exitToolStripMenuItem});
			this.toolStripMenuItem1.Name = "toolStripMenuItem1";
			this.toolStripMenuItem1.Size = new System.Drawing.Size(37, 20);
			this.toolStripMenuItem1.Text = "&File";
			// 
			// saveToolStripMenuItem
			// 
			this.saveToolStripMenuItem.Name = "saveToolStripMenuItem";
			this.saveToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
			this.saveToolStripMenuItem.Text = "&Save ...";
			this.saveToolStripMenuItem.Click += new System.EventHandler(this.saveToolStripMenuItem_Click);
			// 
			// toolStripMenuItem2
			// 
			this.toolStripMenuItem2.Name = "toolStripMenuItem2";
			this.toolStripMenuItem2.Size = new System.Drawing.Size(149, 6);
			// 
			// exitToolStripMenuItem
			// 
			this.exitToolStripMenuItem.Name = "exitToolStripMenuItem";
			this.exitToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
			this.exitToolStripMenuItem.Text = "E&xit";
			// 
			// openToolStripMenuItem
			// 
			this.openToolStripMenuItem.Name = "openToolStripMenuItem";
			this.openToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
			this.openToolStripMenuItem.Text = "&Open ...";
			this.openToolStripMenuItem.Click += new System.EventHandler(this.openToolStripMenuItem_Click);
			// 
			// Form1
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(784, 583);
			this.Controls.Add(this.uxSaveAsNew);
			this.Controls.Add(this.uxSave);
			this.Controls.Add(this.label4);
			this.Controls.Add(this.uxAnswers);
			this.Controls.Add(this.uxDelete);
			this.Controls.Add(this.label3);
			this.Controls.Add(this.uxCurrentQuestion);
			this.Controls.Add(this.label2);
			this.Controls.Add(this.uxQuestions);
			this.Controls.Add(this.uxAddSet);
			this.Controls.Add(this.uxDeleteSet);
			this.Controls.Add(this.label1);
			this.Controls.Add(this.uxQuestionSetList);
			this.Controls.Add(this.uxMainMenu);
			this.MainMenuStrip = this.uxMainMenu;
			this.Name = "Form1";
			this.Text = "iLMica Question Maker";
			this.Load += new System.EventHandler(this.Form1_Load);
			this.uxMainMenu.ResumeLayout(false);
			this.uxMainMenu.PerformLayout();
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
		private System.Windows.Forms.TextBox uxAnswers;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.Button uxSaveAsNew;
		private System.Windows.Forms.Button uxSave;
		private System.Windows.Forms.MenuStrip uxMainMenu;
		private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem1;
		private System.Windows.Forms.ToolStripMenuItem saveToolStripMenuItem;
		private System.Windows.Forms.ToolStripSeparator toolStripMenuItem2;
		private System.Windows.Forms.ToolStripMenuItem exitToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem openToolStripMenuItem;
    }
}

