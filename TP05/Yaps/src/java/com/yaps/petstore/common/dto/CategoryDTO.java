package com.yaps.petstore.common.dto;

public class CategoryDTO implements DataTransfertObject{
	
	public CategoryDTO() {
	}
	
	public CategoryDTO(String id, String name, String description) {
		_id=id;
		this._name=name;
		this._description=description;
	}

	@Override
	public String toString() {
		return "CategoryDTO [_id=" + _id + ", _name=" + _name + ", _description=" + _description + "]";
	}
	
	public String getId() {
		return _id;
	}
	public void setId(String id) {
		_id = id;
	}
	public String getName() {
		return _name;
	}
	public void setName(String name) {
		_name = name;
	}
	public String getDescription() {
		return _description;
	}
	public void setDescription(String description) {
		_description = description;
	}
	
	private String _id;
	private String _name;
	private String _description;

}
