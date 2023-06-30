package api.dtos;

import java.time.LocalDate;

public record ClientRecordDto(String name, LocalDate birthday, String phone, String mail) {

}
