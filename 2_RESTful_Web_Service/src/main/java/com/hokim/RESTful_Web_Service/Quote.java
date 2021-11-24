package com.hokim.RESTful_Web_Service;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//json객체를 오브젝트로 맵핑할때 특정 항목을 무시함 (ex:@JsonIgnoreProperties(id)하면 id가 무시됨 )
@JsonIgnoreProperties(ignoreUnknown = true)
// json객체를 오브젝트로 맵핑할때 선언되지 않은 타입이 있으면 에러가 발생하는데
// (ignoreUnknown = true) 하면 알려지지 않은 타입을 무시함으로써 해당 오류를 발생시키지 않음
public class Quote {
    private String type;
    private Value value;

    public Quote() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }


    //데이터를 직접 맵핑하려면 API에서 반환된 JSON 문서의 키와 정확히 동일하도록 변수 이름을 지정해야 함
    // JSON의 변수 이름과 키가 일치하지 않는 경우 @JsonProperty를 이용해 정확한 키를 지정
    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
