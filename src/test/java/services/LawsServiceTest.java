package services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.find.law.portal.core.content.laws.LawType;
import com.find.law.portal.repositories.LawRepository;
import com.find.law.portal.repositories.entities.laws.LawEntity;
import com.find.law.portal.repositories.entities.laws.LawPartEntity;
import com.find.law.portal.services.LawsService;
import com.find.law.portal.services.impl.LawsServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class LawsServiceTest {
    private LawRepository lawRepository;

    @BeforeEach
    public void Initialize() {
        lawRepository = mock(LawRepository.class);
    }

    @Test
    public void example() {
        assertTrue(true);
    }
}
