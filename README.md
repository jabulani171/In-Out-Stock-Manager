# Stock Management Application

## Overview
A desktop application for supermarket stock management built with Spring Boot (backend) and Angular (frontend). Features include stock file uploads, user management, sales processing with receipt printing, and low stock notifications via email and WhatsApp.

## Prerequisites
- Java 17
- Node.js 16+
- Maven
- Angular CLI
- H2 Database
- Twilio account for WhatsApp notifications
- SMTP server (e.g., Gmail) for email notifications

## Setup
1. **Backend**:
   - Navigate to `/backend`
   - Update `application.properties` with Twilio and email credentials
   - Run `mvn spring-boot:run`

2. **Frontend**:
   - Navigate to `/frontend`
   - Run `npm install`
   - Run `ng serve`

3. **Run Application**:
   - Execute `./scripts/run-app.sh`

## Features
- **Stock Management**: Upload stock files (CSV format: productName,barcode,quantity,price), manage stock status (INSTOCK, DAMAGED, EXPIRED, RETURNED, SOLD).
- **User Management**: Admins can add employees who can view stock and process sales.
- **Sales Processing**: Scan products, calculate totals, process payments, print receipts, and track daily sales.
- **Notifications**: Sends WhatsApp and email alerts to admins when stock is below 10 units, checked every 5 hours.

## Desktop Deployment
- Build the Angular app: `ng build`
- Package with Electron: Install Electron and configure `main.js` to load the Angular app.
