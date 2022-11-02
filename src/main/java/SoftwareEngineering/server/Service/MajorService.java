package SoftwareEngineering.server.Service;

import SoftwareEngineering.server.Domain.Major;
import SoftwareEngineering.server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final UserRepository userRepository;

    public List<Major> getMajorList(){
        return userRepository.findAll();
    }
}
