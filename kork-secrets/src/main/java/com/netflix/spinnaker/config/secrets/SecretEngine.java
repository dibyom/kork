/*
 * Copyright 2019 Armory, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.config.secrets;

/**
 * SecretEngines contain service specific functionality in order to decrypt EncryptedSecrets.
 * Identifiers are used in order to identify which SecretEngine an EncryptedSecret refers to.
 * SecretEngines are used by the SecretManager in order to decrypt a given secret.
 */
public interface SecretEngine {
  String identifier();

  String decrypt(EncryptedSecret encryptedSecret);

  /**
   * In order for a secretEngine to decrypt an EncryptedSecret, it may require extra information (e.g.
   * decryptionKey, location, encryptionMethod, etc). This method takes an EncryptedSecret and
   * validates it contains the required information the service needs for decryption. Parameter names
   * should not contain ':'
   *
   * @param encryptedSecret
   * @return boolean indicating the EncryptedSecret contains the correct parameters
   * @throws InvalidSecretFormatException
   */
  void validate(EncryptedSecret encryptedSecret);

  default EncryptedSecret encrypt(String secretToEncrypt) {
    throw new UnsupportedOperationException("This operation is not supported");
  }

  void clearCache();
}
