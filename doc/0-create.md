```
npx @react-native-community/cli init ReactNativePoC --version 0.75.4
```

Do you want to install CocoaPods now? -- Choose "yes"

An error in CocoaPods installation:
```
✖ Installing CocoaPods dependencies with New Architecture
```
We can ignore it.

```
cd ReactNativePoC
yarn
cd ios
pod install
cd ..
yarn start
yarn ios
adb reverse tcp:8081 tcp:8081
yarn android
```
