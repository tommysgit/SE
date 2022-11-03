package SoftwareEngineering.server.Service;

import SoftwareEngineering.server.Domain.Major;
import SoftwareEngineering.server.Repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;

    public List<Major> getMajorList(){
        return majorRepository.findAll();
    }
}
