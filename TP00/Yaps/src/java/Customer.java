
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;

public class Customer {
	
	public Customer(String id, String firstname, String lastname) {
		this._id = id;
		this._firstname = firstname;
		this._lastname= lastname;
	}
	
	public Customer(String id, String firstname, String lastname, String telephone, String street1, String street2,
			String city, String state, String zipcode, String country, String mail) {
		this._id = id;
		this._firstname = firstname;
		this._lastname = lastname;
		this._telephone = telephone;
		this._street1 = street1;
		this._street2 = street2;
		this._city = city;
		this._state = state;
		this._zipcode = zipcode;
		this._country = country;
		this._mail = mail;
	}
	
	
	public boolean checkData() {
		if(_id == null || "".equals(_id))
			return false;
		if (_firstname == null || "".equals(_firstname))
			return false;
		if (_lastname == null || "".equals(_lastname))
			return false;
		return true;
	}

	public boolean checkId(String id) {
		if (_id == null || "".equals(_id)){
			return false;
		}
		return true;
	}

	public String getCheckDataError() {
		if (!checkId(this._id))
			return "Invalid id";
		if (_firstname == null || "".equals(_firstname))
			return "Invalid first name";
		if (_lastname == null || "".equals(_lastname))
			return "Invalid last name";
		return "right";
	}

//	1. 必须包含一个并且只有一个符号“@”   
//	2. 第一个字符不得是“@”或者“.”   
//	3. 不允许出现“@.”或者.@   
//	4. 结尾不得是字符“@”或者“.”   
//	5. 允许“@”前的字符中出现“＋”   
//	6. 不允许“＋”在最前面，或者“＋@” 

	public boolean checkMail() {
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
		Pattern regex = Pattern.compile(check);  
		Matcher matcher = regex.matcher(this._mail);  
		if(matcher.matches())
			return true;
		return false;
	}

	public static Customer find(String id) {
		return _hashmap.get(id);
	}

	public static boolean remove(String id) {
		if(find(id)==null)
			return false;
		_hashmap.remove(id);
		return true;
	}

	public static boolean insert(Customer customer) {
		_hashmap.put(customer.getId(), customer);
		return true;
	}


	public String getId() {
		return _id;
	}
	public void setId(String id) {
		_id = id;
	}
	public String getFirstname() {
		return _firstname;
	}
	public void setFirstname(String firstname) {
		_firstname = firstname;
	}
	public String getLastname() {
		return _lastname;
	}
	public void setLastname(String lastname) {
		_lastname = lastname;
	}
	public String getTelephone() {
		return _telephone;
	}
	public void setTelephone(String telephone) {
		_telephone = telephone;
	}
	public String getStreet1() {
		return _street1;
	}
	public void setStreet1(String street1) {
		_street1 = street1;
	}
	public String getStreet2() {
		return _street2;
	}
	public void setStreet2(String street2) {
		_street2 = street2;
	}
	public String getCity() {
		return _city;
	}
	public void setCity(String city) {
		_city = city;
	}
	public String getState() {
		return _state;
	}
	public void setState(String state) {
		_state = state;
	}
	public String getZipcode() {
		return _zipcode;
	}
	public void setZipcode(String zipcode) {
		_zipcode = zipcode;
	}
	public String getCountry() {
		return _country;
	}
	public void setCountry(String country) {
		_country = country;
	}
	public String getMail() {
		return _mail;
	}
	public void setMail(String mail) {
		_mail = mail;
	}

	private String _id;
    private String _firstname;
    private String _lastname;
    private String _telephone;
    private String _street1;
    private String _street2;
    private String _city;
    private String _state;
    private String _zipcode;
    private String _country;
    private String _mail;
    
    private static Map<String, Customer> _hashmap = new HashMap<String, Customer>();

}
