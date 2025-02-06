/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import type {PropsWithChildren} from 'react';
import {
  SafeAreaView,
  StatusBar,
  Text,
  TouchableOpacity,
  useColorScheme,
  View,
} from 'react-native';

import {MicroBlink} from '../NativeModules';
import {styles} from './style';

function App(): React.JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';

  const onPressScan = React.useCallback(() => {
    console.log('Scanning...');
    MicroBlink.scanReceipt(true).then((result) => {
      console.log('Scan result:', result);
    });
  }, []);

  return (
    <SafeAreaView>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
      />
      <View style={styles.containerView}>
        <TouchableOpacity onPress={onPressScan}>
          <Text style={styles.scanButtonText}>Scan</Text>
        </TouchableOpacity>
      </View>
    </SafeAreaView>
  );
}

export default App;
