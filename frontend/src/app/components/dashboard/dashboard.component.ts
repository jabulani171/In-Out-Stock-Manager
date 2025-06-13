import { Component, OnInit } from '@angular/core';
import { SaleService } from '../../services/sale.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  dailyTotal: number = 0;

  constructor(private saleService: SaleService) {}

  ngOnInit(): void {
    const today = new Date().toISOString().split('T')[0];
    this.saleService.getDailyTotal(today).subscribe(total => {
      this.dailyTotal = total;
    });
  }
}
