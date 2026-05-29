# Java 8 Project Structure

## 📁 Complete File Organization

```
Java8/
│
├── 📄 README.md                                    ← Start here! Master index & learning path
├── 📄 PROJECT_STRUCTURE.md                         ← This file (project organization)
│
├── 📂 docs/                                        ← All documentation
│   ├── 📄 QUICK_REFERENCE.md                      ← Quick syntax lookup
│   │
│   ├── 📂 theory/                                  ← Detailed theory (numbered for order)
│   │   ├── 📄 01_FunctionalProgramming.md         ← Imperative vs Declarative, Pure Functions
│   │   ├── 📄 02_LambdaSyntax.md                  ← All lambda syntax variations
│   │   ├── 📄 03_FunctionalInterfaces.md          ← (To be created)
│   │   ├── 📄 04_StreamBasics.md                  ← (To be created)
│   │   └── 📄 05_AdvancedStreams.md               ← (To be created)
│   │
│   └── 📂 examples/                                ← Quick code snippets (future)
│
├── 📂 src/                                         ← All Java source code
│   │
│   ├── 📂 basics/                                  ← Fundamental Java 8 concepts
│   │   ├── 📄 App.java                            ← Original lambda example
│   │   ├── 📄 FunctionalProgrammingExamples.java  ← Imperative vs Declarative examples
│   │   ├── 📄 LambdaSyntaxExamples.java           ← All lambda syntax forms
│   │   └── 📄 FunctionalInterfaceExamples.java    ← Predicate, Function, Consumer, etc.
│   │
│   ├── 📂 streams/                                 ← Stream API examples
│   │   ├── 📄 BasicStreamOperations.java          ← filter, map, forEach, etc.
│   │   ├── 📄 AdvancedStreamOperations.java       ← groupBy, collectors, reduce
│   │   └── 📄 StreamPracticeProblems.java         ← Practice exercises
│   │
│   └── 📂 advanced/                                ← Advanced topics (future)
│       ├── 📄 MethodReferenceExamples.java        ← (To be created)
│       ├── 📄 OptionalExamples.java               ← (To be created)
│       └── 📄 ParallelStreamExamples.java         ← (To be created)
│
├── 📂 bin/                                         ← Compiled .class files (auto-generated)
│   ├── 📂 basics/
│   ├── 📂 streams/
│   └── 📂 advanced/
│
└── 📂 lib/                                         ← External libraries (if needed)
```

---

## 🎯 How to Navigate

### **For Learning (Follow This Order):**

1. **Start:** Read `README.md` for overview
2. **Theory:** Read files in `docs/theory/` (numbered order)
3. **Practice:** Run corresponding files in `src/`
4. **Reference:** Use `docs/QUICK_REFERENCE.md` for quick lookup

### **For Interview Prep:**

1. **Theory:** Focus on `docs/theory/` Q&A sections
2. **Practice:** Solve problems in `src/streams/AdvancedStreamOperations.java`
3. **Quick Review:** Use `docs/QUICK_REFERENCE.md`

### **For Quick Reference:**

- **Syntax lookup:** `docs/QUICK_REFERENCE.md`
- **Detailed explanation:** `docs/theory/`
- **Working examples:** `src/`

---

## 📚 File Mapping (Theory → Code)

| Theory File | Code Examples | Description |
|-------------|---------------|-------------|
| `01_FunctionalProgramming.md` | `basics/FunctionalProgrammingExamples.java` | Imperative vs Declarative |
| `02_LambdaSyntax.md` | `basics/LambdaSyntaxExamples.java` | All lambda syntax forms |
| `QUICK_REFERENCE.md` | `basics/FunctionalInterfaceExamples.java` | Predicate, Function, etc. |
| `QUICK_REFERENCE.md` | `streams/BasicStreamOperations.java` | Stream basics |
| `QUICK_REFERENCE.md` | `streams/AdvancedStreamOperations.java` | Collectors, groupBy |

---

## 🗂️ File Naming Convention

### **Theory Files (Markdown):**
- Format: `##_TopicName.md`
- Location: `docs/theory/`
- Numbered for learning order
- Contains detailed explanations + interview Q&A

### **Code Files (Java):**
- Format: `TopicNameExamples.java` or `TopicNameOperations.java`
- Location: `src/basics/`, `src/streams/`, or `src/advanced/`
- Contains runnable examples
- Well-commented for clarity

---

## 🚀 Quick Commands

### **Compile & Run Examples:**

```bash
# Basics
cd src/basics
javac LambdaSyntaxExamples.java
java LambdaSyntaxExamples

# Streams
cd src/streams
javac BasicStreamOperations.java
java BasicStreamOperations
```

### **Compile All:**

```bash
# From project root
javac -d bin src/basics/*.java
javac -d bin src/streams/*.java
```

---

## 📊 Current Status

### ✅ Completed Topics:
- Functional Programming Theory
- Lambda Syntax (all variations)
- Functional Interfaces (Predicate, Function, etc.)
- Stream Basics (filter, map, forEach)
- Advanced Streams (groupBy, collectors)

### 🔄 In Progress:
- Method References
- Optional Class

### 📋 Planned:
- Date/Time API
- Parallel Streams
- CompletableFuture
- Default & Static Methods

---

## 💡 Tips for Organization

### **Adding New Topics:**

1. **Create theory file:** `docs/theory/##_TopicName.md`
2. **Create code file:** `src/appropriate-folder/TopicNameExamples.java`
3. **Update README.md:** Add to learning path
4. **Update this file:** Add to structure

### **Keeping It Clean:**

- ✅ Theory in `docs/theory/`
- ✅ Code in `src/` subfolders
- ✅ Use numbered theory files
- ✅ Use descriptive code file names
- ✅ Keep README.md updated

---

## 🔍 Finding Files

### **Need Theory?**
→ Check `docs/theory/` (numbered files)

### **Need Examples?**
→ Check `src/basics/` or `src/streams/`

### **Need Quick Syntax?**
→ Check `docs/QUICK_REFERENCE.md`

### **Need Overview?**
→ Check `README.md`

---

## 📝 File Descriptions

### **Documentation Files:**

| File | Purpose |
|------|---------|
| `README.md` | Master index, learning path, progress tracker |
| `PROJECT_STRUCTURE.md` | This file - project organization |
| `QUICK_REFERENCE.md` | Quick syntax lookup, common patterns |
| `docs/theory/*.md` | Detailed theory with interview Q&A |

### **Code Files:**

| File | Purpose |
|------|---------|
| `basics/App.java` | Original lambda example (custom interfaces) |
| `basics/FunctionalProgrammingExamples.java` | Imperative vs Declarative examples |
| `basics/LambdaSyntaxExamples.java` | All lambda syntax variations |
| `basics/FunctionalInterfaceExamples.java` | Built-in functional interfaces |
| `streams/BasicStreamOperations.java` | Basic stream operations |
| `streams/AdvancedStreamOperations.java` | Advanced collectors, groupBy |
| `streams/StreamPracticeProblems.java` | Practice exercises |

---

## 🎓 Learning Workflow

```
1. Read Theory
   ↓
2. Run Examples
   ↓
3. Modify Code
   ↓
4. Solve Practice Problems
   ↓
5. Review Quick Reference
   ↓
6. Repeat for Next Topic
```

---

**Well-Organized Project = Easy Learning! 📚✨**

*Last Updated: May 27, 2026*