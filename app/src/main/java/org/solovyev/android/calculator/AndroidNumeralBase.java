/*
 * Copyright 2013 serso aka se.solovyev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Contact details
 *
 * Email: se.solovyev@gmail.com
 * Site:  http://se.solovyev.org
 */

package org.solovyev.android.calculator;

import jscl.NumeralBase;
import org.solovyev.android.calculator.keyboard.KeyboardUi;
import org.solovyev.android.calculator.units.CalculatorNumeralBase;
import org.solovyev.android.views.dragbutton.DirectionDragButton;
import org.solovyev.android.views.dragbutton.DragDirection;

import javax.annotation.Nonnull;

public enum AndroidNumeralBase {

    bin(CalculatorNumeralBase.bin) {
        @Override
        public void toggleButtons(boolean show, @Nonnull KeyboardUi ui) {
            toggleButton(show, ui.button0);
            toggleButton(show, ui.button1);
        }
    },

    oct(CalculatorNumeralBase.oct) {
        @Override
        public void toggleButtons(boolean show, @Nonnull KeyboardUi ui) {
            bin.toggleButtons(show, ui);
            toggleButton(show, ui.button2);
            toggleButton(show, ui.button3);
            toggleButton(show, ui.button4);
            toggleButton(show, ui.button5);
            toggleButton(show, ui.button6);
            toggleButton(show, ui.button7);
        }
    },

    dec(CalculatorNumeralBase.dec) {
        @Override
        public void toggleButtons(boolean show, @Nonnull KeyboardUi ui) {
            oct.toggleButtons(show, ui);
            toggleButton(show, ui.button8);
            toggleButton(show, ui.button9);
        }
    },

    hex(CalculatorNumeralBase.hex) {
        @Override
        public void toggleButtons(boolean show, @Nonnull KeyboardUi ui) {
            dec.toggleButtons(show, ui);
            toggleLeftButton(show, ui.button1);
            toggleLeftButton(show, ui.button2);
            toggleLeftButton(show, ui.button3);
            toggleLeftButton(show, ui.button4);
            toggleLeftButton(show, ui.button5);
            toggleLeftButton(show, ui.button6);
        }

        protected final void toggleLeftButton(boolean show, @Nonnull DirectionDragButton button) {
            button.showDirectionText(show, DragDirection.left);
            button.invalidate();
        }
    };

    @Nonnull
    private final CalculatorNumeralBase calculatorNumeralBase;

    AndroidNumeralBase(@Nonnull CalculatorNumeralBase calculatorNumeralBase) {
        this.calculatorNumeralBase = calculatorNumeralBase;
    }

    @Nonnull
    public static AndroidNumeralBase valueOf(@Nonnull NumeralBase nb) {
        for (AndroidNumeralBase androidNumeralBase : values()) {
            if (androidNumeralBase.calculatorNumeralBase.getNumeralBase() == nb) {
                return androidNumeralBase;
            }
        }

        throw new IllegalArgumentException(nb + " is not supported numeral base!");
    }

    public abstract void toggleButtons(boolean show, @Nonnull KeyboardUi ui);

    protected final void toggleButton(boolean show, @Nonnull DirectionDragButton button) {
        button.setShowText(show);
        button.invalidate();
    }

    @Nonnull
    public NumeralBase getNumeralBase() {
        return calculatorNumeralBase.getNumeralBase();
    }
}
