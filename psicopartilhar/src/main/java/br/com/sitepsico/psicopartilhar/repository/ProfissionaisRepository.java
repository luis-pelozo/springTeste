package br.com.sitepsico.psicopartilhar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.sitepsico.psicopartilhar.models.Profissionais;

public interface ProfissionaisRepository extends JpaRepository<Profissionais, Long> {
    
}
