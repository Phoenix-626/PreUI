package com.itsaky.preui.models;

public class Colors
{
	String name, code;

	public Colors(String name, String code)
	{
		this.name = name;
		this.code = code;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public String getName(){
		return this.name;
	}
	
}
