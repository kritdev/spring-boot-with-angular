import { Component, OnInit } from '@angular/core';
import { ReportService } from '../report.service';

@Component({
  selector: 'app-pdf-viewer',
  templateUrl: './pdf.component.html',
  styleUrls: ['./pdf.component.css']
})
export class PdfComponent implements OnInit {

  pdfSource =  null;
  isShow = false;

  constructor(private reportService: ReportService) { }

  ngOnInit(): void {
  }

  showReport() {
    this.isShow = true;

    this.reportService.retrieveReport()
    .subscribe(
        result => this.pdfSource = result,
        err => alert(err)
    );
  }
}
