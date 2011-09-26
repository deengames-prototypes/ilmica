using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QuestionMaker
{
	class Question
	{
		public string Text { get; set; }
		public string Answers { get; set; }
		public Dictionary<QuestionMetaDataType, string> MetaData { get; set; }

		public override string ToString()
		{
			if (Text.Length > 50)
			{
				return Text.Substring(0, 50);
			}
			else
			{
				return Text;
			}
		}		
	}

	public enum QuestionMetaDataType
	{
		Information
	}
}
