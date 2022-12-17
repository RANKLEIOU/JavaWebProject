package com.ajv.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ajv
 * @Title:Result
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/1314:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity<T> {

	private static final String SUCCESS = "SUCCESS";
	private static final String FAILED = "FAILED";

	private String statu;
	private String msg;
	private T data;

	public static <Type> ResultEntity<Type> success(){
		return new ResultEntity<>(SUCCESS,null,null);
	}

	public static <Type> ResultEntity<Type> success(Type data){
		return new ResultEntity<>(SUCCESS,null,data);
	}

	public static <Type> ResultEntity<Type> falied(String msg){
		return new ResultEntity<>(FAILED,msg,null);
	}
}
