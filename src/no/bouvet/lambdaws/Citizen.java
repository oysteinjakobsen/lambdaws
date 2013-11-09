package no.bouvet.lambdaws;


public class Citizen implements Person {

	private String name;
	private int age;
	private Gender gender;
    private City city;
	
	public Citizen(String name, int age, Gender gender, City city) {
		this.name = name;
		this.age = age;
		this.gender = gender;
        this.city = city;
	}
	
	@Override
	public Integer getAge() {
		return age;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Gender getGender() {
		return gender;
	}

    @Override
    public City getCity() {
        return city;
    }
	
	@Override
	public String toString() {
		return name + '(' + age + ',' + gender + ") - " + city;
	}
}
