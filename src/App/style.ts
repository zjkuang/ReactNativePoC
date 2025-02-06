import {StyleSheet} from 'react-native';

export const styles = StyleSheet.create({
  containerView: {
    width: '100%',
    height: '100%',
    alignItems: 'center', // along secondary axis
    justifyContent: 'center', // along primary axis
  },
  scanButtonText: {
    borderColor: 'lightgrey',
    borderRadius: 5,
    borderWidth: 1,
    paddingHorizontal: 8,
    paddingVertical: 2,
  },
});
