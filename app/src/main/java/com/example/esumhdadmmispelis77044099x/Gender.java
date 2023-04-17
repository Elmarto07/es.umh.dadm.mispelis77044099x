package com.example.esumhdadmmispelis77044099x;

public class Gender {

    private String gender;
    private Integer id;
    private String customGender;

    public Gender(){

    }

    /*public Gender(String gender){
        this.gender = gender;
    }*/

    public Gender(Integer id,String gender ){
        this.id = id;
        this.gender = gender;
    }

    //getters y setters

    public String getGender(){
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        this.customGender = gender;
        return customGender;
    }


}
