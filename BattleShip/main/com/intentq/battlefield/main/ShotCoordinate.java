package com.intentq.battlefield.main;

public class ShotCoordinate {
	
	private int x;
	private int y;
	
	
	public ShotCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null) 
			return false;
		if(obj == this) 
			return true;
		if(!(obj instanceof ShotCoordinate))
			return false;
		
		ShotCoordinate shot = (ShotCoordinate) obj;
		
		if(this.getX()==shot.getX() &&
			this.getY()==shot.getY()) {
				return true;
			}
		return false;	
	}
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + this.getX();
		result = prime * result + this.getY();
		return result;
	}
}
