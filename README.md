# Sui Family Genealogy App

## Introduction
An Android application designed for managing and displaying family genealogy information. Built with modern Android development technologies, it provides an intuitive user interface and comprehensive genealogy management features.

## Key Features
- 📱 View and manage genealogy information
- 🔍 Search by name and generation number
- 👨‍👩‍👧‍👦 Family relationship network visualization
- 📊 Detailed personal information display
- 📥 Excel data import support
- 👥 View family member relationships (parents, spouse, children, etc.)

## Tech Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM
- **Dependency Injection**: Dagger Hilt
- **Database**: Room Database
- **Asynchronous Programming**: Kotlin Coroutines + Flow
- **Additional Technologies**:
  - Android Jetpack Components
  - ViewModel
  - StateFlow
  - Navigation Component

## Project Structure 
```
app/src/main/
├── java/com/example/sui/
│   ├── feature/
│   │   ├── data/           # Data Layer
│   │   │   ├── datasource/ # Data Sources
│   │   │   ├── repository/ # Repositories
│   │   │   └── util/       # Utilities
│   │   ├── domain/         # Domain Layer
│   │   │   ├── model/      # Models
│   │   │   └── use_case/   # Use Cases
│   │   ├── di/             # Dependency Injection
│   │   └── ui/             # UI Layer
└── res/                    # Resources
```


## Core Features
1. **Main Screen**
   - Genealogy title display
   - Family member list
   - Filter search panel
   - Bottom sheet filter

2. **Detail Screen**
   - Personal information
   - Family relationship network diagram
   - Parents, spouse, and children information
   - Relationship visualization with connecting lines

3. **Data Management**
   - Excel data import
   - Database storage and queries
   - Multi-dimensional data filtering

## Data Model
Core data model (Sui) includes:
- Generation
- ID
- First Name
- Superior ID (Father)
- Mother ID
- Relationship Type
- Personal Information:
  - Birth date
  - Death date
  - Residence place
  - Burial place
  - Profession
  - Personal introduction
  - Additional notes

## Development Requirements
- Android Studio Arctic Fox or higher
- Kotlin 1.5.0 or higher
- Android SDK 21+
- Gradle 7.0+

## Getting Started
1. Clone the repository
2. Open project in Android Studio
3. Sync Gradle dependencies
4. Run on emulator or physical device
