# RenArt Case Study

A full-stack monorepo with an Angular front-end and Spring Boot back-end that serves product data from a JSON file (no database required).

---

## Project Structure

```
/
├── backend-api/      # Spring Boot API
├── frontend-app/     # Angular single-page application
├── docker-compose.yml
└── README.md
```

---

## Requirements

### Backend
- Read product data from `products.json`
- Calculate **price** using formula:
  ```
  price = (popularityScore + 1) * weight * goldPrice
  ```
- Fetch `goldPrice` from an external API with caching
- Provide endpoint: `GET /api/products`

### Frontend
- Fetch and display products in a grid or list
- Include:
  - **Carousel** for product images
  - **Color picker** to select variants
  - **Rating display** (0–5 scale, one decimal)
  - **Filters** for price and rating (bonus)

---

## Running the Application

### Using Docker Compose (recommended)
```bash
git clone <REPO_URL>
cd <REPO_NAME>
docker-compose up --build
```
- **API**: http://localhost:8080/api/products
- **UI**:  http://localhost:4200

### Manual Setup

1. **Backend**
   ```bash
   cd backend-api
   ./mvnw clean package
   ./mvnw spring-boot:run
   ```
2. **Frontend**
   ```bash
   cd frontend-app
   npm install
   ng serve --open
   ```

---

## Configuration

- **Environment variable** for Spring:
  - `SPRING_PROFILES_ACTIVE` (e.g., `dev`, `prod`)
- **Docker Compose** can be customized via `docker-compose.override.yml`

---

## Tech Stack

- **Backend**: Java 17, Spring Boot 3.x, Spring Web, Caffeine Cache, Springdoc OpenAPI
- **Frontend**: Angular 15+, Angular Material, SwiperJS, ngx-color-picker
- **Containerization & CI**: Docker, Docker Compose, GitHub Actions

---

## Notes

- No database is required; data is loaded from `products.json`.

---

> **Case Study:** RenArt – A clean, well-documented example of a modern Angular & Spring Boot application.
