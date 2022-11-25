package SoftwareEngineering.server.Service;

import SoftwareEngineering.server.Domain.Field;
import SoftwareEngineering.server.Dto.FieldDto;
import SoftwareEngineering.server.Repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldService {
    private final FieldRepository fieldRepository;

    public List<FieldDto.FiledListResDto> getFieldList(){
        List<Field> fieldList = fieldRepository.findAll();
        List<FieldDto.FiledListResDto> filedListResDtoList = new ArrayList<>();
        fieldList.forEach(f -> {
            filedListResDtoList.add(FieldDto.FiledListResDto.builder().fieldIdx(f.getFieldIdx()).name(f.getName()).build());
        });
        return filedListResDtoList;
    }
}
