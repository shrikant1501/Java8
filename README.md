# Java 8 Learning Project

A comprehensive guide to learning Java 8 features with theory and practical examples.

## 📁 Project Structure

```
Java8/
├── README.md                          ← You are here (Master Index)
├── docs/
│   ├── theory/                        ← All theory/markdown files
│   │   ├── 01_FunctionalProgramming.md
│   │   ├── 02_LambdaSyntax.md
│   │   └── 03_StreamAPI.md
│   └── examples/                      ← Quick reference examples
│
├── src/
│   ├── basics/                        ← Basic Java 8 concepts
│   │   ├── LambdaExamples.java
│   │   ├── FunctionalInterfaceExamples.java
│   │   └── MethodReferenceExamples.java
│   │
│   ├── streams/                       ← Stream API examples
│   │   ├── BasicStreamOperations.java
│   │   ├── AdvancedStreamOperations.java
│   │   └── StreamPracticeProblems.java
│   │
│   └── advanced/                      ← Advanced topics
│       ├── OptionalExamples.java
│       ├── ParallelStreams.java
│       └── CustomCollectors.java
│
└── bin/                               ← Compiled classes (auto-generated)
```

---

## 📚 Learning Path

### **Phase 1: Fundamentals**
1. [Functional Programming Basics](docs/theory/01_FunctionalProgramming.md)
   - Imperative vs Declarative
   - Pure Functions
   - Side Effects
   - [Code Examples](src/basics/FunctionalProgrammingExamples.java)

2. [Lambda Expressions](docs/theory/02_LambdaSyntax.md)
   - Basic Syntax
   - Parameter Variations
   - Variable Capture
   - [Code Examples](src/basics/LambdaSyntaxExamples.java)

3. [Functional Interfaces](docs/theory/03_FunctionalInterfaces.md)
   - Predicate, Function, Consumer, Supplier
   - BiFunction, BiPredicate
   - [Code Examples](src/basics/FunctionalInterfaceExamples.java)

### **Phase 2: Stream API**
4. [Stream Basics](docs/theory/04_StreamBasics.md)
   - Creating Streams
   - Intermediate Operations
   - Terminal Operations
   - [Code Examples](src/streams/BasicStreamOperations.java)

5. [Advanced Streams](docs/theory/05_AdvancedStreams.md)
   - Collectors
   - Grouping & Partitioning
   - Reduce Operations
   - [Code Examples](src/streams/AdvancedStreamOperations.java)

### **Phase 3: Advanced Topics**
6. [Method References](docs/theory/06_MethodReferences.md)
7. [Optional Class](docs/theory/07_Optional.md)
8. [Date/Time API](docs/theory/08_DateTime.md)
9. [Parallel Streams](docs/theory/09_ParallelStreams.md)

---

## 🚀 Quick Start

### **1. Study Theory**
```bash
# Read markdown files in order
docs/theory/01_FunctionalProgramming.md
docs/theory/02_LambdaSyntax.md
...
```

### **2. Run Examples**
```bash
# Compile and run examples
cd src/basics
javac LambdaSyntaxExamples.java
java LambdaSyntaxExamples
```

### **3. Practice**
- Modify existing examples
- Solve practice problems
- Create your own examples

---

## 📖 Current Topics Covered

### ✅ Completed
- [x] Functional Programming Theory
- [x] Lambda Syntax (All variations)
- [x] Functional Interfaces (Predicate, Function, etc.)
- [x] Stream API Basics
- [x] Advanced Stream Operations
- [x] Variable Capture & Effectively Final

### 🔄 In Progress
- [ ] Method References
- [ ] Optional Class
- [ ] Date/Time API

### 📋 Planned
- [ ] Parallel Streams
- [ ] Custom Collectors
- [ ] CompletableFuture
- [ ] Default & Static Methods in Interfaces

---

## 🎯 Interview Preparation

### **Key Files for Interview Prep:**
1. **Theory:** `docs/theory/01_FunctionalProgramming.md` - Core concepts
2. **Theory:** `docs/theory/02_LambdaSyntax.md` - Lambda syntax rules
3. **Practice:** `src/streams/AdvancedStreamOperations.java` - Common interview problems

### **Common Interview Questions:**
- What is functional programming?
- Why were lambdas introduced in Java?
- What is effectively final?
- Difference between map() and flatMap()?
- How to find duplicates using streams?
- How to group data using collectors?

**All answers with examples are in the theory files!**

---

## 📝 How to Use This Project

### **For Learning:**
1. Read theory file (markdown)
2. Run corresponding code example
3. Modify code to experiment
4. Solve practice problems

### **For Interview Prep:**
1. Review theory files (especially Q&A sections)
2. Run all examples to understand output
3. Practice explaining concepts out loud
4. Solve advanced stream problems

### **For Reference:**
- Use README as index
- Jump to specific topics
- Quick syntax lookup in theory files

---

## 🛠️ File Naming Convention

### **Theory Files (Markdown):**
- `01_TopicName.md` - Numbered for learning order
- Located in `docs/theory/`

### **Code Files (Java):**
- `TopicNameExamples.java` - Basic examples
- `TopicNamePractice.java` - Practice problems
- `AdvancedTopicName.java` - Advanced concepts

### **Organization:**
- `src/basics/` - Fundamental concepts
- `src/streams/` - Stream API related
- `src/advanced/` - Advanced topics

---

## 📊 Progress Tracker

| Topic | Theory | Examples | Practice | Status |
|-------|--------|----------|----------|--------|
| Functional Programming | ✅ | ✅ | ✅ | Complete |
| Lambda Syntax | ✅ | ✅ | ✅ | Complete |
| Functional Interfaces | ✅ | ✅ | ⏳ | In Progress |
| Stream Basics | ✅ | ✅ | ⏳ | In Progress |
| Advanced Streams | ✅ | ✅ | ⏳ | In Progress |
| Method References | ⏳ | ⏳ | ⏳ | Planned |
| Optional | ⏳ | ⏳ | ⏳ | Planned |

---

## 🔗 Quick Links

### **Theory Documents:**
- [Functional Programming](docs/theory/01_FunctionalProgramming.md)
- [Lambda Syntax](docs/theory/02_LambdaSyntax.md)

### **Code Examples:**
- [Lambda Examples](src/basics/LambdaSyntaxExamples.java)
- [Functional Programming Examples](src/basics/FunctionalProgrammingExamples.java)
- [Stream Examples](src/streams/BasicStreamOperations.java)
- [Advanced Stream Examples](src/streams/AdvancedStreamOperations.java)

### **Practice Problems:**
- [Stream Practice](src/streams/StreamPracticeProblems.java)

---

## 💡 Tips

### **For Effective Learning:**
- ✅ Follow the numbered order in theory files
- ✅ Run examples after reading theory
- ✅ Experiment by modifying code
- ✅ Solve practice problems
- ✅ Review interview Q&A sections

### **For Interview Preparation:**
- ✅ Focus on theory Q&A sections
- ✅ Understand common patterns
- ✅ Practice explaining concepts
- ✅ Memorize key syntax rules
- ✅ Solve advanced stream problems

---

## 📞 Need Help?

- Check theory files for detailed explanations
- Run examples to see concepts in action
- Review interview Q&A sections
- Practice problems have solutions

---

## 🎓 Learning Outcomes

After completing this project, you will be able to:
- ✅ Explain functional programming concepts
- ✅ Write lambda expressions confidently
- ✅ Use Stream API for data processing
- ✅ Solve common interview problems
- ✅ Apply Java 8 features in real projects

---

**Happy Learning! 🚀**

*Last Updated: May 27, 2026*
