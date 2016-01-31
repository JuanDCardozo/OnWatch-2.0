#include <pebble.h>

static Window *window;
static TextLayer *layer;
static TextLayer *layer1;

int level = 1; 

enum {
  KEY_BUTTON_EVENT = 0,
  BUTTON_EVENT_UP = 1,
  BUTTON_EVENT_SELECT = 2,
  BUTTON_EVENT_DOWN = 3,
  KEY_VIBRATION = 4
};

static void in_received_handler(DictionaryIterator *iter, void *context)
{
  Tuple *t = dict_read_first(iter);
  if(t)
  {
    vibes_short_pulse();  
  }
}



void send_int(uint8_t key, uint8_t cmd)
{
  DictionaryIterator *iter;
  app_message_outbox_begin(&iter);
  Tuplet value = TupletInteger(key, cmd);
  dict_write_tuplet(iter, &value);
  app_message_outbox_send();
}

void up_click_handler(ClickRecognizerRef recognizer, void *context)
{
  if(level <= 2)
    level += 1;
  if(level == 1)
    {
      
      text_layer_set_text(layer1, "SAFE");
    }
    
  if(level == 2)
    {
      text_layer_set_text(layer1, "ALERT");
    }
  if(level == 3)
    {
      text_layer_set_text(layer1, "THREAT");
    }
    send_int(KEY_BUTTON_EVENT, BUTTON_EVENT_UP);
}

static void select_long_click_handler(ClickRecognizerRef recognizer, void *context)
{
  text_layer_set_text(layer1, "911");
  level = 4;
  send_int(KEY_BUTTON_EVENT, BUTTON_EVENT_SELECT);
}

static void select_long_click_release_handler(ClickRecognizerRef recognizer, void *context)
{
  text_layer_set_text(layer1, "911");
  level = 4;
  send_int(KEY_BUTTON_EVENT, BUTTON_EVENT_DOWN);
} 

static void down_click_handler(ClickRecognizerRef recognizer, void *context)
{
  if(level >= 2)
    level -= 1;
  if(level == 1)
    text_layer_set_text(layer1, "SAFE");
  if(level == 2)
    text_layer_set_text(layer1, "ALERT");
  if(level == 3)
    text_layer_set_text(layer1, "THREAT");
  send_int(KEY_BUTTON_EVENT, BUTTON_EVENT_DOWN);
}

static void click_config_provider(void *context)
{
  window_single_click_subscribe(BUTTON_ID_UP, up_click_handler);
  window_long_click_subscribe(BUTTON_ID_SELECT, 2000, select_long_click_handler, select_long_click_release_handler);
  window_single_click_subscribe(BUTTON_ID_DOWN, down_click_handler);
}



static void window_load(Window *window)
{
  Layer *window_layer = window_get_root_layer(window); 
  GRect window_bounds = layer_get_bounds(window_layer);
  
  layer = text_layer_create(GRect(5, 0, window_bounds.size.w - 5, window_bounds.size.h));
  text_layer_set_font(layer, fonts_get_system_font(FONT_KEY_GOTHIC_24_BOLD));
  text_layer_set_text(layer, "Current Safety level:");
  text_layer_set_text_alignment(layer, GTextAlignmentCenter);
  text_layer_set_overflow_mode(layer, GTextOverflowModeWordWrap);
  layer_add_child(window_layer, text_layer_get_layer(layer));
  
  layer1 = text_layer_create(GRect(5, 75, window_bounds.size.w - 5, window_bounds.size.h));
  text_layer_set_font(layer1, fonts_get_system_font(FONT_KEY_BITHAM_30_BLACK));
  text_layer_set_text(layer1, "SAFE");
  text_layer_set_text_alignment(layer1, GTextAlignmentCenter);
  text_layer_set_overflow_mode(layer1, GTextOverflowModeWordWrap);
  layer_add_child(window_layer, text_layer_get_layer(layer1));
}

void window_unload(Window *window)
{
   text_layer_destroy(layer); 
}  


static void init()
{
	window = window_create();
	window_set_window_handlers(window, (WindowHandlers)
	{
		.load = window_load,
		.unload = window_unload
	});
  app_message_register_inbox_received(in_received_handler);
  app_message_open(512, 512);
  window_set_click_config_provider(window, click_config_provider);
  window_stack_push(window, true);
}

static void deinit()
{
	window_destroy(window);
}

int main(void)
{
	init();
	app_event_loop();
	deinit();
}