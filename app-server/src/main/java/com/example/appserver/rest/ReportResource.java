package com.example.appserver.rest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@RestController
@RequestMapping("/api")
public class ReportResource {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @GetMapping("/report/pdf")
  public byte[] generateReportpdf(HttpServletResponse response)
      throws IOException, SQLException, JRException {
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", String.format("attachment;filename=\"sample.pdf\""));
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    Connection connection = jdbcTemplate.getDataSource().getConnection();
    File file = ResourceUtils.getFile("classpath:reports/sample_report.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

    Map<String, Object> parameters = new HashMap<>();
    // parameters.put("id", clientID);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
    JasperExportManager.exportReportToPdfStream(jasperPrint, out);

    connection.close();
    return out.toByteArray();
  }
}