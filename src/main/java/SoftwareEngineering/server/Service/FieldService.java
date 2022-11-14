package SoftwareEngineering.server.Service;

import SoftwareEngineering.server.Domain.Field;
import SoftwareEngineering.server.Repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldService {
    private final FieldRepository fieldRepository;

    public List<Field> getFieldList(){return fieldRepository.findAll();}
}
