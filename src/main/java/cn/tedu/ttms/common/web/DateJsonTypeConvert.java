package cn.tedu.ttms.common.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateJsonTypeConvert extends JsonSerializer<Date> {

	@Override
	public void serialize(Date arg0, JsonGenerator arg1, SerializerProvider arg2)
			throws IOException, JsonProcessingException {

		//自定义日期格式
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		//将数据以json的
		arg1.writeString(sdf.format(arg0));
		
	}

	


}
