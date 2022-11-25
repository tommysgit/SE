package SoftwareEngineering.server.Service;

import SoftwareEngineering.server.Domain.Major;
import SoftwareEngineering.server.Dto.MajorDto;
import SoftwareEngineering.server.Repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;

    public List<MajorDto.MajorListResDto> getMajorList(){
        List<Major> majorList = majorRepository.findAll();
        List<MajorDto.MajorListResDto> majorListResDtoList = new ArrayList<>();
        majorList.forEach(m ->{
            majorListResDtoList.add(MajorDto.MajorListResDto.builder().majorIdx(m.getMajorIdx()).name(m.getName()).build());
        } );
        return majorListResDtoList;
    }
}
