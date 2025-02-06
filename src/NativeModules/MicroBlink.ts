import { NativeModules } from 'react-native';

interface MicroBlinkNativeModule {
  scanReceipt: (storeUserFrames: boolean) => Promise<Record<string, any>>;
}

const nativeModule: MicroBlinkNativeModule = NativeModules.MicroBlinkNativeModule;

export const MicroBlink: MicroBlinkNativeModule = {
  async scanReceipt(storeUserFrames: boolean) {
    return nativeModule.scanReceipt(storeUserFrames).catch((error: Error) => {
      console.error(`MicroBlink.scanReceipt: ${error.message}`);
      return { result: 'error', error };
    });
  },
};
