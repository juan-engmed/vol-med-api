package medvoll.spring3_alura.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select p from Paciente p
            where
            p.id = :id
            and
            p.ativo = true
            """)
    Optional<Paciente> findAtivoById(@Param("id") Long id);


}
