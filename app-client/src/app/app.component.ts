import { Component } from '@angular/core';
import { DataService } from './data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  data = null;

  constructor(private dataService: DataService) {
  }

  ngOnInit(): void {
    this.dataService
      .retrieveData()
      .subscribe(
        result => { this.data = result as any[]; },
        err => { alert(err); }
      );

  }
}
