# Pengeboks.dk 💼🔐

**Pengeboks** is a secure deposit platform tailored for the Danish rental market. It helps tenants and landlords establish a trustworthy relationship through verified identities and token-based escrow deposits.

> 🧠 MVP focused – authentication via MitID sandbox, secure deposit tracking with tokens, and a user dashboard for viewing incoming and outgoing deposits.

---

## 🔧 Stack

- Java (Spring Boot)
- PostgreSQL (via Supabase)
- REST APIs (microservices)
- React + MUI (Frontend)
- MitID Sandbox (Authentication)
- PDF generation service
- Docker (planned)

---

## 🧱 Architecture

This application is structured as microservices:

- **Auth Service**: handles authentication (MitID/Simulated)
- **User Service**: manages user profiles, including photo uploads
- **Deposit Service**: manages deposits, releases, and secure logging
- **PDF Service**: generates deposit receipts in PDF format
- **API Gateway**: entry point for frontend clients
- **Frontend**: built with React + MUI

All services interact with Supabase (PostgreSQL + Storage).

---

## 📂 Folder Structure

´´´bash
pengeboks/ │ ├── api-gateway/ ├── auth-service/ ├── user-service/ ├── deposit-service/ ├── pdf-service/ ├── frontend/ └── docs/
´´´


---

## 🚀 Getting Started

1. Clone the repo
2. Set up Supabase project
3. Configure environment variables for services
4. Start services individually or with Docker (WIP)
