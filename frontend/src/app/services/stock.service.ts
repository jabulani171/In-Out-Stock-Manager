import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StockService {
  private apiUrl = 'http://localhost:8080/api/stocks';

  constructor(private http: HttpClient) {}

  uploadStockFile(file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post(`${this.apiUrl}/upload`, formData);
  }

  getStockByBarcode(barcode: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/barcode/${barcode}`);
  }

  updateStockStatus(barcode: string, status: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/status/${barcode}`, { status });
  }
}
