# Mastering Android Paging 3

[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.7.x-blue.svg)](https://kotlinlang.org)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

This is an application demonstrating how to use the Android Paging 3 library to display huge data sets in chunks by combining data from the network with local cache using Room and Realm databases.

# ✍️ Medium
Check out the [article](https://medium.com/p/dea6435b6183) with a detailed explanation of the implementation.

# 📷 Preview
![map](https://user-images.githubusercontent.com/26262185/194933048-0de3bfc0-5f02-41e3-9be8-addf3b362646.gif)

# 🏛️ Architecture
![mastering-paging-3-architecture](https://user-images.githubusercontent.com/26262185/194933282-8b91d7c0-6475-4224-bf21-38a4d036f122.png)

# 🛠 Tech Stack
- [Retrofit](https://square.github.io/retrofit/) - networking
- [Jetpack](https://developer.android.com/jetpack)
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - lifecycle aware UI related data holder
  - [Room](https://developer.android.com/jetpack/androidx/releases/room) - offline cache
  - [Hilt](https://dagger.dev/hilt/) - dependency injection
- [Realm](https://github.com/realm/realm-java) - offline cache
- [Coil](https://github.com/coil-kt/coil) - image loading

# 📃 License

```
MIT License

Copyright (c) 2022 miel3k

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
