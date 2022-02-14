package com.example.appserver.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.appserver.repository.DataMessageRepository;
import com.example.appserver.rest.model.DataMessage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/api")
public class ReportResource {

  @Autowired
  DataMessageRepository dataMessageRepository;

  @Autowired
  ResourceLoader resourceLoader;

  @GetMapping("/report/pdf")
  public byte[] generateReportpdf(HttpServletResponse response)
      throws IOException, SQLException, JRException {
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", String.format("attachment;filename=\"sample.pdf\""));
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    InputStream file =
        resourceLoader.getResource("classpath:reports/sample_report.jrxml").getInputStream();
    JasperReport jasperReport = JasperCompileManager.compileReport(file);

    List<DataMessage> dataList = dataMessageRepository.findAll();
    Map<String, Object> parameters = new HashMap<>();
    // parameters.put("id", clientID);
    JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataList);

    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
    JasperExportManager.exportReportToPdfStream(jasperPrint, out);

    return out.toByteArray();
  }
}
