/*
 *  Copyright 2019 Arcus Project.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package arcus.app.common.cards.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import arcus.app.R;
import arcus.app.common.cards.CenteredTextCard;


public class CenteredTextCardItemView extends BaseCardItemView<CenteredTextCard> {
    public CenteredTextCardItemView(Context context) {
        super(context);
    }

    public CenteredTextCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CenteredTextCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void build(@NonNull CenteredTextCard card) {
            super.build(card);

            final CenteredTextCard.OnClickCallaback callaback = card.getCallaback();
            TextView title = (TextView)this.findViewById(R.id.title_text);
            title.setText(card.getTitle());
            if (callaback != null) {
                title.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callaback.onTitleClicked();
                    }
                });
            }
            if(card.getTitleColor() != -1) {
                title.setTextColor(card.getTitleColor());
            }

            CardView cardView = (CardView) findViewById(R.id.cardView);
            if (cardView != null) {
                if (card.isTransparentBackground()) {
                    cardView.setCardBackgroundColor(Color.TRANSPARENT);
                }
                else {
                    cardView.setCardBackgroundColor(getContext().getResources().getColor(R.color.overlay_white_with_10));
                }
            }

            if (card.getDescription() != null) {
                TextView description = (TextView) findViewById(R.id.description);
                description.setVisibility(VISIBLE);
                description.setText(card.getDescription());
                description.setTextColor(card.getDescriptionColor());
                if (card.getDescriptionBackground() != -1) {
                    description.setBackgroundResource(card.getDescriptionBackground());
                }
                if (callaback != null) {
                    description.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callaback.onDescriptionClicked();
                        }
                    });
                }
            }

            if (card.isDividerShown()) {
                showDivider();
            }
        }

    private void showDivider() {
        View divider = findViewById(R.id.divider);
        if (divider != null) divider.setVisibility(View.VISIBLE);
    }
}
