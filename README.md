# WattBill: Your Java-Powered Electricity Billing Companion ⚡

## 🚀 Introduction

Welcome to **WattBill**, an electricity billing system built using Java and Swing! Designed to streamline the electricity billing process, this project combines functionality, ease of use, and a sleek GUI to manage billing for admins and customers alike. Whether you're allocating meter numbers or paying bills, WattBill makes it watt-simple!

*Add a screenshot of the main UI here (e.g., login screen, dashboard, or billing page)*

---

## 🛠️ Features

### 🔑 Admin and Customer Login
- Secure login options for both administrators and customers.
- Admin access to manage customer details and electricity billing data.

*Add an icon for secure login*

### 📜 Customer Management
- Customer account creation and seamless meter number allocation.
- Quick view of customer details with an intuitive interface.

*Include an image showing the customer management interface*

### ⚡ Meter Installation
- Supports both residential and commercial meter installations.
- Dynamic meter allocation ensures flexibility and accuracy.

*Add a picture showing meter installation options*

### 💸 Bill Calculation and Payments
- Automatically calculates electricity bills with:
  - Meter number-based consumption tracking
  - Service charges, taxes, and additional fees like Swachh Bharat Cess
- Integration with popular payment platforms like Paytm and Sapoch.

*Add a screenshot or graphic of the bill calculation process*

### 🔍 Advanced Admin Controls
- Easy customer detail search and bill updates.
- Deposit tracking and payment status monitoring.
- Printable billing summaries for record-keeping.
- Downloadable reports via an integrated **Printer class** for details, customer lists, and more.

*Add a screenshot of the admin dashboard with advanced controls*

### 🖱️ Intuitive User Interface
- Feature-rich menu bar for quick navigation.
- Clear role differentiation between admin and customer portals.
- Shortcuts to quickly navigate between various system options.

*Add an image of the Swing GUI interface with the menu bar*

### 📂 Database Integration
- **MySQL** is used to store and manage customer and billing data securely and efficiently.

*Include a screenshot of the database schema or a visual representation of database integration*

### 🛠️ Additional Tools
- Built-in **Notepad** shortcut for quick notes and reminders.
- Integrated **Calculator** shortcut for on-the-go calculations.

---

## 📸 Screenshots (Coming Soon!)

*Visualize the simplicity and power of WattBill!*

---

## 📂 Project Structure

```
WattBill
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── BillDetails.java
│   │   │   ├── CalculateBill.java
│   │   │   ├── Conn.java
│   │   │   ├── CustomerDetails.java
│   │   │   ├── DepositDetails.java
│   │   │   ├── GenerateBill.java
│   │   │   ├── Login.java
│   │   │   ├── MeterInfo.java
│   │   │   ├── NewCustomer.java
│   │   │   ├── PayBill.java
│   │   │   ├── Paytm.java
│   │   │   ├── Project.java
│   │   │   ├── Signup.java
│   │   │   ├── Splash.java
│   │   │   ├── UpdateInformation.java
│   │   │   └── ViewInformation.java
├── resources
│   └── icons
├── README.md
```

---

## 🌟 Technologies Used

- **Java**: Core programming language
- **Swing**: GUI framework for creating a rich user experience
- **MySQL**: Database management system

---

## 💻 How to Run

1. **Clone this repository**:
   ```bash
   git clone https://github.com/juhiagarwal2003/WattBill.git
   ```

2. **Install Java**: Ensure that you have **Java 8 or later** installed on your system.

3. **Set up MySQL**: 
   - Install MySQL if you haven't already.
   - Import the provided schema file (located in the `resources/` directory) into your MySQL database.

4. **Configure Database Connection**:
   - In the `Conn.java` file, set up your MySQL database credentials (username, password, host).

5. **Build and Run**:
   - Open the project in your favorite Java IDE (e.g., IntelliJ, Eclipse, NetBeans).
   - Build and run the `Project.java` file.

6. **Payment Integration**:
   - For Paytm and Sapoch payments, you'll need to set up API keys in the respective files (`Paytm.java`).
   - Follow the documentation of each platform to integrate their payment gateways.

---

Let **WattBill** light up your billing process! 💡

---
