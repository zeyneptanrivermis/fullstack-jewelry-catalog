
# Full-Stack Jewelry Catalog App

Demo Link: https://zeynep-tanrivermis-renart-case-stud.vercel.app 

---

## Project Overview

RenArt is a modern, database-free full-stack web app built with Angular (frontend) and Spring Boot (backend), delivering real-time, gold-price-based product pricing and a responsive, interactive UI.

---

## Monorepo Structure

```
/
├── backend-api/        # Spring Boot API
├── frontend-app/       # Angular SPA
├── docker-compose.yml  # (Optional use for containerization)
└── README.md
```

---

## 1. Core Features

- **Dynamic Product Pricing**: Product prices are calculated in real-time based on popularity score, weight, and the current gold price.
- **Interactive Product Carousel**: Built using SwiperJS, users can swipe or click arrows to switch between product images.
- **Color Picker**: Users can select different product colors (Yellow, Rose, White Gold); images update dynamically.
- **Real-Time Gold Price API with Caching**: Live gold price is fetched from an external service (e.g., goldapi.io) and cached using Caffeine for 5 minutes.
- **Automated Background Price Refresh**: Gold price updates every few hours automatically via scheduled tasks.
- **Advanced Filtering (Bonus)**: Price and rating filters supported through query parameters.
- **Responsive UI Design**: Mobile-first layout using Angular Material for consistent cross-device experience.
- **User Feedback & Error Handling**: API call failures are handled with visual loading states and clean error messages.

---

## 2. Technical Architecture & Design

### 2.1 Backend (Spring Boot)

- **Layered Architecture**: Controller → Service → Utility → Model
- **Product Data Source**: Reads from `products.json` (no database used).
- **Dynamic Pricing Service**: `(popularityScore + 1) * weight * goldPrice`
- **External API Integration**: Fetches gold price from third-party API (configurable via environment variable).
- **Caching**: Caffeine-based in-memory cache (TTL: 5 mins).
- **Filtering Support**: Supports query params for filtering by `minPrice`, `maxPrice`, and `minRating`.
- **OpenAPI Docs**: Swagger UI available at `/swagger-ui.html`

### 2.2 Frontend (Angular)

- **Component-Based Architecture**: ProductList, ProductCard, Carousel, Rating, ColorPicker
- **HttpClient Service**: Pulls data from backend `/api/products` endpoint
- **Swiper Carousel**: Image slider supporting swipe and click
- **Rating Display**: Converts `popularityScore` (0–1) to 0–5 scale with one decimal
- **Color Picker**: Changes image based on selected color
- **Filters UI (Bonus)**: Sidebar filtering by price & rating
- **Responsive Layout**: Angular Material grid, styled according to mock design

---

## Getting Started (Manual Setup)

### Backend
```bash
cd backend-api
./mvnw clean package
./mvnw spring-boot:run
```
→ http://localhost:8080/api/products

### Frontend
```bash
cd frontend-app
cd renart-frontend
npm install
ng serve --open
```
→ http://localhost:4200

---

## Tech Stack

| Layer     | Technologies |
|-----------|--------------|
| Backend   | Java 21, Spring Boot 3.x, Spring Web, Springdoc OpenAPI, Caffeine Cache |
| Frontend  | Angular 15+, Angular Material, SwiperJS, ngx-color-picker |
| DevOps    | Docker, Docker Compose, GitHub Actions (optional) |

---

## Sample Product JSON

```json
{
  "name": "Engagement Ring 1",
  "popularityScore": 0.85,
  "weight": 2.1,
  "images": {
    "yellow": "image-url",
    "rose": "image-url",
    "white": "image-url"
  }
}
```

---

## Notes

- All product data is sourced from a static `products.json` file.
- No database or ORM is required.
- Each product has 3 color options with separate images.
- Pricing is real-time, tied to current gold market value.

