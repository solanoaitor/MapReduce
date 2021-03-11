public class KeyValue<String,Integer> {

	private String key;
	private Integer value;
	
	public KeyValue(String k, Integer v) {
		key=k;
		value=v;
	}
	
	public String getKey() {
		return key;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer v) {
		value=v;
	}
}
