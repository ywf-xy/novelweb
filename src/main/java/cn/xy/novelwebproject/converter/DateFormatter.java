package cn.xy.novelwebproject.converter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class DateFormatter implements Formatter<Date> {

		private static final String PATTERN = "yyyy-MM-dd";
		private SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);

		@Override
		public Date parse (String source, Locale locale) throws ParseException {

				Date date = sdf.parse(source);
				return date;
		}

		@Override
		public String print (Date date, Locale locale) {
				String result = sdf.format(date);
				return result;
		}
}
