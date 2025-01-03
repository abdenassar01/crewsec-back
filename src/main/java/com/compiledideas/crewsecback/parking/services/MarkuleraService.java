package com.compiledideas.crewsecback.parking.services;

import com.compiledideas.crewsecback.parking.models.Markulera;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

@Qualifier("markulera_jpa_service")
public interface MarkuleraService {
    Page<Markulera> findAllMarkuleras(Integer page, Integer limit);
    Markulera findMarkuleraById(Long id);
    Markulera createMarkulera(Markulera Markulera);
    Markulera updateMarkulera(Long id, Markulera Markulera);
    Markulera deleteMarkulera(Long id);
}
