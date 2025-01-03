package com.compiledideas.crewsecback.parking.services.implimentations;

import com.compiledideas.crewsecback.exceptions.ResourceNotFoundException;
import com.compiledideas.crewsecback.parking.models.Report;
import com.compiledideas.crewsecback.parking.repositories.ParkingRepository;
import com.compiledideas.crewsecback.parking.repositories.ReportRepository;
import com.compiledideas.crewsecback.parking.services.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("report_jpa_service")
public class ReportServiceImpl implements ReportService {

    private final ReportRepository repository;
    private final ParkingRepository parkingRepository;

    @Override
    public Page<Report> findAllReports(Integer page, Integer limit) {
        return repository.findAll(PageRequest.of(page, limit));
    }

    @Override
    public Report findReportById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Report", "id", id));
    }

    @Override
    public Report findReportByParking(Long userId) {
        var parking = parkingRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Parking", "id", userId));
        return repository.findByParking(parking).orElseThrow(() -> new ResourceNotFoundException("Report", "user id", userId));
    }


    @Override
    public Report createReport(Report report) {
        return repository.save(report);
    }

    @Override
    public Report updateReport(Long id, Report report) {
        report.setId(id);
        return repository.save(report);
    }

    @Override
    public Report deleteReport(Long id) {
        var report = findReportById(id);
        repository.deleteById(id);
        return report;
    }
}
