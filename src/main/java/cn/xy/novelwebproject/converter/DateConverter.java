package cn.xy.novelwebproject.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateConverter implements Converter<String, Date> {


		private static final String PATTERN = "yyyy-MM-dd";

		@Override
		public Date convert (String source) {

				SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);

				Date date = null;
				try {
						date = sdf.parse(source);
						System.out.println(source);
				} catch (ParseException e) {
						e.printStackTrace();
				}

				return date;
		}
}
