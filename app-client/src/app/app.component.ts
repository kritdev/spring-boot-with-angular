import { Component, ViewChild } from '@angular/core';
import { DataService } from './data.service';
import { PdfComponent } from './pdf/pdf.component';
import { ReportService } from './report.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  dataList = null;
  dataMessage = {content:""};

  @ViewChild(PdfComponent)
  pdfComponent: PdfComponent;

  constructor(
    private dataService: DataService,
    private reportService: ReportService) {
  }

  ngOnInit(): void {
    this.clear();
    this.retrieveData();
  }

  retrieveData() {
    this.dataService.retrieveData()
      .subscribe(
        result => { this.dataList = result as any[]; },
        err => { alert(err); }
      );
  }

  clear() {
    this.dataMessage = {content:""};
  }

  saveMessage(data) {
    this.dataService.create(data)
    .subscribe(
      result => {
        alert('success');
        this.ngOnInit();
      },
      err => alert(err)
    );
  }

  hasId() {
    return this.dataMessage["id"] != undefined;
  }

  get(item) {
    this.dataService.find(item.id)
    .subscribe(
      result => this.dataMessage = result as any,
      err => alert(err)
    );
  }

  update(item) {
    this.dataService.update(item)
    .subscribe(
      result => {          
        alert('success');
        this.ngOnInit();
      },
      err => alert(err)
    );
  }

  delete(item) {
    this.dataService.delete(item.id)
    .subscribe(
      result => this.retrieveData(),
      err => alert(err)
    );
  }

  showReport(){
    this.pdfComponent.showReport();
  }

  downloadReport(){
    this.reportService.retrieveReport()
    .subscribe(
        result => {
          //Create a Blob from the PDF Stream
          const file = new Blob([result as any], { type: 'application/pdf' });
          //Build a URL from the file
          const fileURL = URL.createObjectURL(file);
          //Open the URL on new Window
          window.open(fileURL);
        },
        err => alert(err)
    );
  }
}
