package no.bouvet.lambdaws;

public interface Person {

	public static enum Gender {
		MALE, FEMALE
	};
	
	String getName();
	
	Integer getAge();
	
	Gender getGender();

    City getCity();

	default boolean isAdult() {
		return getAge() >= 18;
	}
}
