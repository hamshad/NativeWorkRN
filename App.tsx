import React from 'react';
import type { PropsWithChildren } from 'react';
import {
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  TouchableOpacity,
  useColorScheme,
  View,
} from 'react-native';
import { startBackgroundWork } from './src/WorkManagerModule';

function App(): React.JSX.Element {

  return (
    <View style={styles.container}>
      <TouchableOpacity onPress={startBackgroundWork}>
        <Text>Start Work Manager</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f0f0f0'
  }
});

export default App;
