import { Component, OnInit } from '@angular/core';
import { SaleService } from '../../services/sale.service';
import { StockService } from '../../services/stock.service';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html'
})
export class SalesComponent implements OnInit {
  scannedItems: any[] = [];
  totalAmount: number = 0;
  amountReceived: number = 0;
  change: number = 0;

  constructor(private saleService: SaleService, private stockService: StockService) {}

  ngOnInit(): void {}

  scanProduct(barcode: string) {
    this.stockService.getStockByBarcode(barcode).subscribe(stock => {
      if (stock) {
        this.scannedItems.push(stock);
        this.totalAmount += stock.price;
      }
    });
  }

  calculateChange() {
    this.change = this.amountReceived - this.totalAmount;
    this.saleService.processSale({
      items: this.scannedItems,
      totalAmount: this.totalAmount,
      amountReceived: this.amountReceived,
      change: this.change,
      saleDate: new Date()
    }).subscribe(sale => {
      this.printSlip(sale);
      this.scannedItems = [];
      this.totalAmount = 0;
      this.amountReceived = 0;
      this.change = 0;
    });
  }

  printSlip(sale: any) {
    const printWindow = window.open('', '_blank');
    printWindow?.document.write(`
      <html>
      <body>
        <h2>Sale Receipt</h2>
        <p>Date: ${sale.saleDate}</p>
        <ul>${sale.items.map((item: any) => `<li>${item.productName}: $${item.price}</li>`).join('')}</ul>
        <p>Total: $${sale.totalAmount}</p>
        <p>Received: $${sale.amountReceived}</p>
        <p>Change: $${sale.change}</p>
      </body>
      </html>
    `);
    printWindow?.document.close();
    printWindow?.print();
  }
}
